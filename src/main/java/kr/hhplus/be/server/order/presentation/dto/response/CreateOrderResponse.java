package kr.hhplus.be.server.order.presentation.dto.response;

import java.math.BigDecimal;
import kr.hhplus.be.server.order.domain.order.OrderStatus;

public record CreateOrderResponse (

    Long orderId,
    Long userId,
    BigDecimal totalPrice,
    OrderStatus status
){

}
