package kr.hhplus.be.server.order.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import kr.hhplus.be.server.order.domain.exception.OrderErrorCode;
import kr.hhplus.be.server.order.domain.exception.OrderException;
import kr.hhplus.be.server.order.domain.order.entity.Order;
import kr.hhplus.be.server.order.domain.order.entity.OrderItem;
import kr.hhplus.be.server.order.domain.repository.OrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

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
}
