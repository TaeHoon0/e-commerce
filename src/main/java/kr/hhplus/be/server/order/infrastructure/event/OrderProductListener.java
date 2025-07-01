package kr.hhplus.be.server.order.infrastructure.event;

import kr.hhplus.be.server.order.application.port.out.OrderProductPort;
import kr.hhplus.be.server.order.domain.order.event.OrderApprovedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class OrderProductListener {

    private final OrderProductPort port;


    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlerOrderApprovedEvent(OrderApprovedEvent event) {

        //port.decreaseStock();
    }
}
