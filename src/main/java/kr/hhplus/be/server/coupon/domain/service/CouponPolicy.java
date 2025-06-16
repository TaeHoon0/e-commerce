package kr.hhplus.be.server.coupon.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;
import kr.hhplus.be.server.coupon.domain.exception.CouponErrorCode;
import kr.hhplus.be.server.coupon.domain.exception.CouponException;
import org.springframework.stereotype.Component;

@Component
public class CouponPolicy {

    public void validate(
        UserCoupon coupon, BigDecimal totalPrice
    ) {

        // 최소 주문 금액 검증
        if (coupon.getMinimumPrice() != null && coupon.getMinimumPrice().compareTo(totalPrice) > 0)
            throw new CouponException(CouponErrorCode.INVALID_COUPON_MINIMUM_PRICE);

        // 만료일자 검증
        if (coupon.getExpireDate() != null && coupon.getExpireDate().isAfter(LocalDateTime.now()))
            throw new CouponException(CouponErrorCode.INVALID_COUPON_EXPIRE_DATE);
    }

    public BigDecimal calculate(
        UserCoupon userCoupon, BigDecimal totalPrice
    ) {

        switch (userCoupon.getType()) {

            case FIX -> {
                return userCoupon.getDiscountAmount();
            }

            case RATE -> {

                BigDecimal discountPercent
                    = userCoupon.getDiscountAmount().divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);

                return totalPrice.multiply(discountPercent).setScale(2, RoundingMode.HALF_UP);
            }

            default -> {
                throw new CouponException(CouponErrorCode.INVALID_COUPON_TYPE);
            }
        }
    }
}
