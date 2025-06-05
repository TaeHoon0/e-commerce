package kr.hhplus.be.server.coupon.presentation.dto.reseponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import kr.hhplus.be.server.coupon.domain.CouponStatus;

public record CouponResponse (

    Long couponId,
    String couponName,
    CouponStatus couponStatus,
    BigDecimal discountAmount,
    BigDecimal minimumPrice,
    LocalDateTime expireDate

){

}
