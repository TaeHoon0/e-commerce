package kr.hhplus.be.server.order.domain.service;

import java.util.List;
import kr.hhplus.be.server.order.domain.order.entity.Order;
import kr.hhplus.be.server.order.domain.order.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {


    public Order createOrder(long userId, List<OrderItem> items) {

        Order order = Order.create(userId, items);

        // 주문 총액 계산
        order.calculateTotalPrice();

        return order;
    }
}
