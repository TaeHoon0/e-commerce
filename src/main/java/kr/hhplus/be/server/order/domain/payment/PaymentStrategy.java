package kr.hhplus.be.server.order.domain.payment;

import kr.hhplus.be.server.order.domain.payment.entity.Payment;

public interface PaymentStrategy {

    PG getPaymentMethod();

    Payment ready(Payment payment);

    void approve(Payment payment);
}
