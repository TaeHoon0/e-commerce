package kr.hhplus.be.server.order.domain.order.event;

import kr.hhplus.be.server.order.domain.order.entity.Order;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderApprovedEvent {

    private final Order order;

    public static OrderApprovedEvent from(Order order) {

        return new OrderApprovedEvent(order);
    }
}
