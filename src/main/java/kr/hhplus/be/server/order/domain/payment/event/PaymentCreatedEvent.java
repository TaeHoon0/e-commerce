package kr.hhplus.be.server.order.domain.payment.event;

import kr.hhplus.be.server.order.domain.payment.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class PaymentCreatedEvent {

    private final Payment payment;

    public static PaymentCreatedEvent from(Payment payment) {

        return new PaymentCreatedEvent(payment);
    }
}
