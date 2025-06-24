package kr.hhplus.be.server.order.domain.order.event;

import kr.hhplus.be.server.order.domain.order.entity.Order;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCreatedEvent {

    private final Order order;

    public static OrderCreatedEvent of(Order order) {

        return new OrderCreatedEvent(order);
    }
}
