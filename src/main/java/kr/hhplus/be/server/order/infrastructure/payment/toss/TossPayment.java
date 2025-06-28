package kr.hhplus.be.server.order.infrastructure.payment.toss;

import java.util.concurrent.ThreadLocalRandom;
import kr.hhplus.be.server.global.utils.CodeUtil;
import kr.hhplus.be.server.order.domain.exception.OrderErrorCode;
import kr.hhplus.be.server.order.domain.exception.OrderException;
import kr.hhplus.be.server.order.domain.payment.PG;
import kr.hhplus.be.server.order.domain.payment.PaymentStrategy;
import kr.hhplus.be.server.order.domain.payment.entity.Payment;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class TossPayment implements PaymentStrategy {

    private final static int RETRY_COUNT = 2;
    private final static int RETRY_DELAY = 500;

    public boolean random() {

        int chance = ThreadLocalRandom.current().nextInt(100);

        return chance >= 30;
    }


    @Override
    public PG getPaymentMethod() {
        return PG.TOSS_PAY;
    }

    @Override
    public Payment ready(Payment payment) {

        System.out.println("토스 페이 결제 준비");

        String tid = CodeUtil.generate();

        payment.ready(tid);

        return payment;
    }

    @Override
    @Retryable(
        retryFor = {Exception.class},
        maxAttempts = RETRY_COUNT,
        backoff = @Backoff(delay = RETRY_DELAY),
        recover = "approveRecover"
    )
    public void approve(Payment payment) {

        if (!random()) {
            throw new OrderException(OrderErrorCode.PAYMENT_PROCESSING_FAILED);
        }

        System.out.println("토스 페이 결제 승인");

        payment.approve();
    }

    @Recover
    public void approveRecover(Exception e, Payment payment) {

        payment.fail();
    }
}
