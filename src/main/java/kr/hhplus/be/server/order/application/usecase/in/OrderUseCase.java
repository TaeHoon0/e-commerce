package kr.hhplus.be.server.order.application.usecase.in;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import kr.hhplus.be.server.order.application.dto.command.ApproveOrderCommand;
import kr.hhplus.be.server.order.application.dto.command.CreateOrderCommand;
import kr.hhplus.be.server.order.application.dto.result.CreateOrderResult;
import kr.hhplus.be.server.order.application.mapper.OrderResultMapper;
import kr.hhplus.be.server.order.application.port.in.OrderPort;
import kr.hhplus.be.server.order.application.port.in.PaymentPort;
import kr.hhplus.be.server.order.application.port.out.OrderCouponPort;
import kr.hhplus.be.server.order.application.port.out.OrderPointPort;
import kr.hhplus.be.server.order.application.port.out.OrderProductPort;
import kr.hhplus.be.server.order.domain.exception.OrderErrorCode;
import kr.hhplus.be.server.order.domain.exception.OrderException;
import kr.hhplus.be.server.order.domain.order.entity.Order;
import kr.hhplus.be.server.order.domain.order.entity.OrderItem;
import kr.hhplus.be.server.order.domain.order.event.OrderCreatedEvent;
import kr.hhplus.be.server.order.domain.repository.OrderQueryRepository;
import kr.hhplus.be.server.order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderUseCase implements OrderPort {

    private final OrderService orderService;

    private final OrderCouponPort orderCouponPort;
    private final OrderPointPort orderPointPort;
    private final OrderProductPort orderProductPort;
    private final PaymentPort paymentPort;

    private final OrderQueryRepository orderQueryRepository;

    private final ApplicationEventPublisher eventPublisher;


    /**
     * <h4>주문 생성</h4>
     * 1. 멱등성 키 조회 <br/> 2. 주문 생성 <br/> 3. 쿠폰 검증<br/>
     * 4. 포인트 검증<br/> 5. 재고 확인<br/> 6. 주문 스냅샷, 아웃박스 생성
     */
    @Override
    @Transactional
    public CreateOrderResult createOrder(CreateOrderCommand command) {

        // 1. 멱등성 키 주문 조회
        Optional<Order> existing = orderQueryRepository.findByIdempotencyKey(command.idempotencyKey());

        if (existing.isPresent()) {
            return OrderResultMapper.toResult(existing.get());
        }

        // 2. 주문 생성
        List<OrderItem> items = command.productCommands().stream()
            .map(cmd ->
                OrderItem.create(cmd.productId(), cmd.productOptionId(), cmd.quantity(), cmd.price()))
            .toList();

        Order order = orderService.createOrder(command.userId(), items);

        // 3. 쿠폰 검증 및 할인 금액 계산
        if (command.couponCommand() != null) {
            if (!orderCouponPort.validateCoupon(command.userId(), command.couponCommand(), order.getTotalPrice()))
                throw new OrderException(OrderErrorCode.INVALID_COUPON_REQUEST);

            BigDecimal couponDiscount = orderCouponPort.calculateDiscountAmount(
                command.userId(), command.couponCommand().couponId(), order.getTotalPrice());

            order.applyCoupon(command.couponCommand().couponId(), couponDiscount);
        }

        // 4. 포인트 검증
        if (command.pointCommand() != null) {
            if (!orderPointPort.validatePoint(command.userId(), command.pointCommand().amount()))
                throw new OrderException(OrderErrorCode.INVALID_POINT_REQUEST);

            order.applyPoint(command.pointCommand().amount());
        }

        // 5. 재고 검증
        if (orderProductPort.validateProduct(command.productCommands()))
            throw new OrderException(OrderErrorCode.INVALID_PRODUCT_REQUEST);

        // 6. 이벤트 생성 outbox, snapshot
        eventPublisher.publishEvent(OrderCreatedEvent.from(order));

        return OrderResultMapper.toResult(order);
    }

    /**
     * 주문 승인
     */
    public ApproveOrderResult approveOrder(
        ApproveOrderCommand command
    ) {

        Order order = orderService.get(command.orderId());

        orderService.validateApprove(order);

        paymentPort.approve(command.userId(), command.orderId(), command.tid());
    }
}
