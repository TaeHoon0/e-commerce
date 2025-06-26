package kr.hhplus.be.server.order.domain.payment;

import java.math.BigDecimal;
import kr.hhplus.be.server.order.domain.exception.OrderErrorCode;
import kr.hhplus.be.server.order.domain.exception.OrderException;
import kr.hhplus.be.server.order.domain.order.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class PaymentPolicy {

    public void validateUserId(Order order, long userId) {

        if (order.getUserId() != userId)
            throw new OrderException(OrderErrorCode.PAYMENT_USER_NOT_MATCHED);
    }

    public void validatePrice(Order order, BigDecimal finalPrice) {

        if(finalPrice.compareTo(order.getFinalPrice()) != 0)
            throw new OrderException(OrderErrorCode.PAYMENT_PRICE_NOT_MATCHED);
    }
}
