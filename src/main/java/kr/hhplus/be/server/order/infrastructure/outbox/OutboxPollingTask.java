package kr.hhplus.be.server.order.infrastructure.outbox;

import java.util.List;
import kr.hhplus.be.server.order.infrastructure.persistence.jpa.OrderOutBoxJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutboxPollingTask {

    private final OrderOutBoxJpaRepository repository;
    private final OutboxPublisher publisher;

    private final static int POLLING_INTERVAL = 1000;

    @Scheduled(fixedDelay = POLLING_INTERVAL)
    public void poll() {

        List<OrderOutbox> outboxes =
            repository.findByOutboxStatusOrderByCreatedDateAsc(OutboxStatus.PENDING);

        outboxes.forEach(publisher::publish);
    }
}
