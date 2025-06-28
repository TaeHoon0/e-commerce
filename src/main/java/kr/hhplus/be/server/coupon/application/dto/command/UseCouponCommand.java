package kr.hhplus.be.server.coupon.application.dto.command;

import java.math.BigDecimal;

public record UseCouponCommand (

    Long couponId,
    Long orderId,
    BigDecimal totalPrice

){
}
