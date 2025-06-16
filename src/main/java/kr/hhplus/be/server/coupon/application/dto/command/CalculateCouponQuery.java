package kr.hhplus.be.server.coupon.application.dto.command;

import java.math.BigDecimal;

public record CalculateCouponQuery(

    Long userId,
    Long couponId,
    BigDecimal totalPrice

) {

}
