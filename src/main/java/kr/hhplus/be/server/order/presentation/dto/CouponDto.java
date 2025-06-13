package kr.hhplus.be.server.order.presentation.dto;

import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record CouponDto(

    @Positive
    Long couponId,

    @Positive
    BigDecimal discountAmount
) {

}
