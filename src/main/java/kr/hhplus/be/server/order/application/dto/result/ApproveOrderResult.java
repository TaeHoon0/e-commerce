package kr.hhplus.be.server.order.application.dto.result;

import java.math.BigDecimal;

public record ApproveOrderResult (

    long orderId,
    BigDecimal totalPrice,
    BigDecimal discountPrice,
    BigDecimal finalPrice

){

}
