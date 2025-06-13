package kr.hhplus.be.server.coupon.application.mapper;

import kr.hhplus.be.server.coupon.application.dto.result.CouponResult;
import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;

public class CouponResultMapper {

    public static CouponResult toResult(UserCoupon entity) {

        return new CouponResult(
            entity.getId(),
            entity.getName(),
            entity.getStatus(),
            entity.getDiscountAmount(),
            entity.getMinimumPrice(),
            entity.getExpireDate()
        );
    }
}
