package kr.hhplus.be.server.order.infrastructure.event;

import kr.hhplus.be.server.global.utils.JsonUtil;
import kr.hhplus.be.server.order.domain.order.event.OrderCreatedEvent;
import kr.hhplus.be.server.order.infrastructure.outbox.OrderOutbox;
import kr.hhplus.be.server.order.infrastructure.persistence.jpa.OrderOutBoxJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class OrderOutboxListener {

    private final OrderOutBoxJpaRepository repository;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handlerOrderCreatedEvent(OrderCreatedEvent event) {

        String payload = JsonUtil.toJson(event);

        OrderOutbox orderOutbox = OrderOutbox.ofCreated(event, payload);

        repository.save(orderOutbox);
    }
}