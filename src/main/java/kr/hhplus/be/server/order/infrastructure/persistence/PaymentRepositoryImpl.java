package kr.hhplus.be.server.order.infrastructure.persistence;

import kr.hhplus.be.server.order.domain.payment.entity.Payment;
import kr.hhplus.be.server.order.domain.repository.payment.PaymentCommandRepository;
import kr.hhplus.be.server.order.infrastructure.persistence.jpa.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentCommandRepository {

    private final PaymentJpaRepository jpaRepository;

    @Override
    public Payment save(Payment payment) {

        return jpaRepository.save(payment);
    }
}
