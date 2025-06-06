package kr.hhplus.be.server.coupon.domain.service;

import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;
import kr.hhplus.be.server.coupon.domain.exception.CouponErrorCode;
import kr.hhplus.be.server.coupon.domain.exception.CouponException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CouponService {

    public void issueCoupon(long userId, UserCoupon coupon) {

        if (coupon.isAssigned()) throw new CouponException(CouponErrorCode.COUPON_ALREADY_ASSIGNED);
        if (coupon.isExpired()) throw new CouponException(CouponErrorCode.COUPON_EXPIRED);

        coupon.issueCoupon(userId);
    }

    public void useCoupon(long orderId, BigDecimal totalPrice, UserCoupon coupon) {

        if (coupon.isNotAvailable()) throw new CouponException(CouponErrorCode.COUPON_NOT_AVAILABLE);
        if (coupon.isExpired()) throw new CouponException(CouponErrorCode.COUPON_EXPIRED);
        if (coupon.isBelowMinimumPrice(totalPrice)) throw new CouponException(CouponErrorCode.INVALID_COUPON_MINIMUM_PRICE);

        coupon.useCoupon(orderId);
    }
}
