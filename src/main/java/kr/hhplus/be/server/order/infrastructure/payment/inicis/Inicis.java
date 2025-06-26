package kr.hhplus.be.server.order.infrastructure.payment.inicis;

import kr.hhplus.be.server.global.utils.CodeUtil;
import kr.hhplus.be.server.order.domain.payment.PG;
import kr.hhplus.be.server.order.domain.payment.PaymentStrategy;
import kr.hhplus.be.server.order.domain.payment.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class Inicis implements PaymentStrategy {


    @Override
    public PG getPaymentMethod() {
        return PG.INICIS;
    }

    @Override
    public Payment ready(Payment payment) {

        System.out.println("이니시스 결제 준비");

        String tid = CodeUtil.generate();

        payment.completeReady(tid);

        return payment;
    }

    @Override
    public void approve(Payment payment) {

        System.out.println("이니시스 결제 승인");
    }
}
