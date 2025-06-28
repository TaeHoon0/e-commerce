package kr.hhplus.be.server.order.infrastructure.outbox;

import kr.hhplus.be.server.order.infrastructure.message.MessageQueue;
import kr.hhplus.be.server.order.infrastructure.persistence.jpa.OrderOutBoxJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutboxPublisher {

    private final OrderOutBoxJpaRepository repository;
    private final MessageQueue messageQueue;

    private final static int RETRY_COUNT = 2;
    private final static int RETRY_DELAY = 500;

    /**
     * 예외 발생하면 재시도 2번, 0.5s 간격 <br>
     * 모두 실패시 recover 메소드 실행
     */
    @Retryable(
        retryFor = {Exception.class},
        maxAttempts = RETRY_COUNT,
        backoff = @Backoff(delay = RETRY_DELAY),
        recover = "recover"
    )
    public void publish(OrderOutbox outbox) {

        messageQueue.send();

        outbox.sent();
        repository.save(outbox);
    }

    @Recover
    public void recover(Exception e, OrderOutbox outbox) {

        outbox.fail();
        repository.save(outbox);
    }
}
