package kr.hhplus.be.server.order.infrastructure.outbox;

import java.util.List;
import kr.hhplus.be.server.order.infrastructure.message.MessageQueue;
import kr.hhplus.be.server.order.infrastructure.persistence.jpa.OrderOutBoxJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutboxPollingTask {

    private final OrderOutBoxJpaRepository repository;
    private final MessageQueue messageQueue;

    private final static int MAX_RETRY_COUNT = 3;

    @Scheduled(fixedDelay = 1000)
    public void poll() throws InterruptedException {

        List<OrderOutbox> outboxes =
            repository.findByOutboxStatusOrderByCreatedDateAsc(OutboxStatus.PENDING);

        if (outboxes.isEmpty())
            return;

        for (OrderOutbox outbox : outboxes) {

            for (int retry = 0; retry < MAX_RETRY_COUNT; retry++) {

                try {

                    messageQueue.send();
                    outbox.sent();
                    repository.save(outbox);

                    break;
                } catch (Exception e) {

                    Thread.sleep(500);

                    if (retry == MAX_RETRY_COUNT - 1) {
                        outbox.fail();
                    }
                }
            }


        }

    }
}
