package kr.hhplus.be.server.order.domain.service;

import java.util.List;
import kr.hhplus.be.server.order.domain.exception.OrderErrorCode;
import kr.hhplus.be.server.order.domain.exception.OrderException;
import kr.hhplus.be.server.order.domain.order.OrderPolicy;
import kr.hhplus.be.server.order.domain.order.entity.Order;
import kr.hhplus.be.server.order.domain.order.entity.OrderItem;
import kr.hhplus.be.server.order.domain.repository.order.OrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderPolicy orderPolicy;

    private final OrderQueryRepository orderQueryRepository;

    public Order createOrder(long userId, List<OrderItem> items) {

        Order order = Order.create(userId, items);

        // 주문 총액 계산
        order.calculateTotalPrice();

        return order;
    }

    public Order get(long orderId) {

        return orderQueryRepository.findById(orderId)
            .orElseThrow(() -> new OrderException(OrderErrorCode.ORDER_NOT_FOUND));
    }

    public void validateApprove(Order order, long userId) {

        orderPolicy.validateUserId(order, userId);
        orderPolicy.validateApproveStatus(order);
    }
}
