package kr.hhplus.be.server.coupon.application.dto.command;

import java.math.BigDecimal;

public record ValidateCouponQuery(

    Long userId,
    Long couponId,
    BigDecimal discountAmount,
    BigDecimal totalPrice
) {

}
