package kr.hhplus.be.server.order.application.dto.result;

import java.math.BigDecimal;
import kr.hhplus.be.server.order.domain.order.OrderStatus;

public record CreateOrderResult(

    Long orderId,
    Long userId,
    BigDecimal totalPrice,
    OrderStatus status
) {

}
