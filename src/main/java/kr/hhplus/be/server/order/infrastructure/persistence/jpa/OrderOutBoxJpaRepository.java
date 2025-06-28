package kr.hhplus.be.server.order.infrastructure.persistence.jpa;

import java.util.List;
import kr.hhplus.be.server.order.infrastructure.outbox.OrderOutbox;
import kr.hhplus.be.server.order.infrastructure.outbox.OutboxStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderOutBoxJpaRepository extends JpaRepository<OrderOutbox, Long> {

    List<OrderOutbox> findByOutboxStatusOrderByCreatedDateAsc(OutboxStatus outboxStatus);
}
