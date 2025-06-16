package kr.hhplus.be.server.order.application.usecase.in;

import java.math.BigDecimal;
import java.util.List;
import kr.hhplus.be.server.order.application.dto.command.CreateOrderCommand;
import kr.hhplus.be.server.order.application.dto.result.CreateOrderResult;
import kr.hhplus.be.server.order.application.port.in.OrderPort;
import kr.hhplus.be.server.order.application.port.out.OrderCouponPort;
import kr.hhplus.be.server.order.application.port.out.OrderPointPort;
import kr.hhplus.be.server.order.application.port.out.OrderProductPort;
import kr.hhplus.be.server.order.domain.exception.OrderErrorCode;
import kr.hhplus.be.server.order.domain.exception.OrderException;
import kr.hhplus.be.server.order.domain.order.entity.Order;
import kr.hhplus.be.server.order.domain.order.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderUseCase implements OrderPort {

    private final OrderCouponPort orderCouponPort;
    private final OrderPointPort orderPointPort;
    private final OrderProductPort orderProductPort;

    /**
     * <h4>주문 생성</h4>
     * 1. 쿠폰 검증<br/> 2. 포인트 검증<br/> 3. 재고 확인<br/>
     * 4. 결제 준비 요청 <br/>5. 주문 준비 생성
     */
    @Override
    @Transactional
    public CreateOrderResult createOrder(CreateOrderCommand command) {

        //TODO 재고 검증, 포인트 검증, 쿠폰 검증, 주문 생성, 스냅샷 생성, 아웃박스 생성, 결제 준비

        List<OrderItem> items = command.productCommands().stream()
            .map(cmd ->
                OrderItem.create(cmd.productId(), cmd.productOptionId(), cmd.quantity(), cmd.price()))
            .toList();

        Order order = Order.create(command.userId(), command.couponCommand().couponId(), items);
        // 총액 계산
        order.calculateTotalPrice();

        BigDecimal discountPrice = BigDecimal.ZERO;
        BigDecimal usePoint = BigDecimal.ZERO;

        // 1. 쿠폰 검증 및 할인 금액 계산
        if (command.couponCommand() != null) {
            if (!orderCouponPort.validateCoupon(command.userId(), command.couponCommand().couponId(), order.getTotalPrice())) {
                throw new OrderException(OrderErrorCode.INVALID_COUPON_REQUEST);
            }

            discountPrice = orderCouponPort.calculateDiscount(
                command.userId(), command.couponCommand().couponId(), order.getTotalPrice());
        }


        // 2. 포인트 검증
        if (command.pointCommand() != null) {
            if (!orderPointPort.validatePoint(command.userId(), command.pointCommand().amount())) {
                throw new OrderException(OrderErrorCode.INVALID_POINT_REQUEST);
            }
        }

        // 3. 재고 검증
        if (orderProductPort.validateProduct(command.productCommands()))
            throw new OrderException(OrderErrorCode.INVALID_PRODUCT_REQUEST);



        return null;
    }
}
