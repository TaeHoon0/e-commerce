package kr.hhplus.be.server.coupon.domain.service;

import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;
import kr.hhplus.be.server.coupon.domain.exception.CouponErrorCode;
import kr.hhplus.be.server.coupon.domain.exception.CouponException;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    public void issueCoupon(long userId, UserCoupon coupon) {

        if(coupon.isAssigned()) throw new CouponException(CouponErrorCode.COUPON_ALREADY_ASSIGNED);
        if(coupon.isExpired()) throw new CouponException(CouponErrorCode.COUPON_EXPIRED);

        coupon.issueCoupon(userId);
    }
}
