package kr.hhplus.be.server.order.infrastructure.persistence.jpa;

import kr.hhplus.be.server.order.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByIdempotencyKey(String idempotencyKey);
}
