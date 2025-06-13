package kr.hhplus.be.server.order.application.provider;

import kr.hhplus.be.server.order.domain.payment.PaymentMethod;
import kr.hhplus.be.server.order.domain.payment.PaymentStrategy;

public interface PaymentProvider {

    /**
     * 결제 타입에 맞는 payments 가져오기
     */
    PaymentStrategy getPaymentStrategy(PaymentMethod method);
}
