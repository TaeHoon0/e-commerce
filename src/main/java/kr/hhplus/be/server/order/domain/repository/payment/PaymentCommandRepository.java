package kr.hhplus.be.server.order.domain.repository.payment;

import kr.hhplus.be.server.order.domain.payment.entity.Payment;

public interface PaymentCommandRepository {

    public Payment save(Payment payment);
}
