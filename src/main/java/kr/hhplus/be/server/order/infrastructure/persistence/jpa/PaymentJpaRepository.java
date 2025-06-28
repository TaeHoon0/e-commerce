package kr.hhplus.be.server.order.infrastructure.persistence.jpa;

import kr.hhplus.be.server.order.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
}
