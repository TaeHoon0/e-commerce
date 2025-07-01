package kr.hhplus.be.server.order.infrastructure.event;

import kr.hhplus.be.server.global.utils.JsonUtil;
import kr.hhplus.be.server.order.domain.order.entity.OrderSnapShot;
import kr.hhplus.be.server.order.domain.order.event.OrderApprovedEvent;
import kr.hhplus.be.server.order.domain.order.event.OrderCreatedEvent;
import kr.hhplus.be.server.order.domain.repository.order.OrderSnapShotCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class OrderSnapShotListener {

    private final OrderSnapShotCommandRepository repository;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleOrderCreatedEvent(OrderCreatedEvent event){

        String json = JsonUtil.toJson(event.getOrder());

        OrderSnapShot snapShot = OrderSnapShot.ofCreated(event.getOrder(), json);

        repository.save(snapShot);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleOrderApprovedEvent(OrderApprovedEvent event){

        String json = JsonUtil.toJson(event.getOrder());

        OrderSnapShot snapShot = OrderSnapShot.ofApproved(event.getOrder(), json);

        repository.save(snapShot);
    }
}
