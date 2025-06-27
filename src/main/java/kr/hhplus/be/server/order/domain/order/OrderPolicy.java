package kr.hhplus.be.server.order.domain.order;

import kr.hhplus.be.server.order.domain.exception.OrderErrorCode;
import kr.hhplus.be.server.order.domain.exception.OrderException;
import kr.hhplus.be.server.order.domain.order.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderPolicy {

    public void validateUserId(Order order, long userId) {

        if (order.getUserId() != userId)
            throw new OrderException(OrderErrorCode.ORDER_USER_NOT_MATCHED);
    }

    public void validateApproveStatus(Order order) {

        if (order.isNotReady())
            throw new OrderException(OrderErrorCode.INVALID_ORDER_STATUS);
    }


}
