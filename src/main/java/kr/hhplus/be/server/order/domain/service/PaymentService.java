package kr.hhplus.be.server.order.domain.service;

import java.math.BigDecimal;
import kr.hhplus.be.server.order.domain.order.entity.Order;
import kr.hhplus.be.server.order.domain.payment.PaymentPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentPolicy paymentPolicy;

    public void validateCreatePayment(
        Order order, long userId, BigDecimal finalPrice
    ) {

        paymentPolicy.validateUserId(order, userId);
        paymentPolicy.validatePrice(order, finalPrice);
    }

    public void validateApprovePayment(
        Order order, long userId, String tid
    ) {

        paymentPolicy.validateUserId(order, userId);
        paymentPolicy.validateApproveStatus(order.getPayment());
        paymentPolicy.validateTid(order.getPayment(), tid);
    }
}
