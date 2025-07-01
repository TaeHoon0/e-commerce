package kr.hhplus.be.server.order.presentation.dto.response;

import java.math.BigDecimal;

public record ApproveOrderResponse (

    long orderId,
    BigDecimal totalPrice,
    BigDecimal discountPrice,
    BigDecimal finalPrice

){

}
