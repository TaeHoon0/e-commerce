package kr.hhplus.be.server.order.infrastructure.event;

import kr.hhplus.be.server.order.application.port.out.OrderCouponPort;
import kr.hhplus.be.server.order.domain.order.entity.Order;
import kr.hhplus.be.server.order.domain.order.event.OrderApprovedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class OrderCouponListener {

    private final OrderCouponPort port;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlerOrderApprovedEvent(OrderApprovedEvent event) {

        Order order = event.getOrder();

        port.useCoupon(order.getCouponId(), order.getId(), order.getTotalPrice());
    }
}
