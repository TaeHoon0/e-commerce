package kr.hhplus.be.server.order.domain.payment;

import java.math.BigDecimal;
import kr.hhplus.be.server.order.domain.exception.OrderErrorCode;
import kr.hhplus.be.server.order.domain.exception.OrderException;
import kr.hhplus.be.server.order.domain.order.entity.Order;
import kr.hhplus.be.server.order.domain.payment.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentPolicy {

    public void validateUserId(Order order, long userId) {

        if (order.getUserId() != userId)
            throw new OrderException(OrderErrorCode.PAYMENT_USER_NOT_MATCHED);
    }

    public void validatePrice(Order order, BigDecimal finalPrice) {

        if (finalPrice.compareTo(order.getFinalPrice()) != 0)
            throw new OrderException(OrderErrorCode.PAYMENT_PRICE_NOT_MATCHED);
    }

    public void validateApproveStatus(Payment payment) {

        if (payment.isNotReady())
            throw new OrderException(OrderErrorCode.PAYMENT_NOT_READY);
    }

    public void validateTid(Payment payment, String tid) {

        if (!payment.getTid().equals(tid))
            throw new OrderException(OrderErrorCode.PAYMENT_TID_NOT_MATCHED);
    }

}
