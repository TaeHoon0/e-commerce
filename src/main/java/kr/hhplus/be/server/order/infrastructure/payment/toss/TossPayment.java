package kr.hhplus.be.server.order.infrastructure.payment.toss;

import kr.hhplus.be.server.order.domain.payment.PG;
import kr.hhplus.be.server.order.domain.payment.PaymentStrategy;
import kr.hhplus.be.server.order.domain.payment.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class TossPayment implements PaymentStrategy {


    @Override
    public PG getPaymentMethod() {
        return PG.TOSS_PAY;
    }

    @Override
    public Payment ready(Payment payment) {

        System.out.println("토스 페이 결제 준비");

        return payment;
    }

    @Override
    public void approve(Payment payment) {

        System.out.println("토스 페이 결제 승인");
    }
}
