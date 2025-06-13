package kr.hhplus.be.server.coupon.application.dto.result;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import kr.hhplus.be.server.coupon.domain.CouponStatus;

public record CouponResult(

    Long couponId,
    String couponName,
    CouponStatus couponStatus,
    BigDecimal discountAmount,
    BigDecimal minimumPrice,
    LocalDateTime expireDate

) {

}
