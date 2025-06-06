package kr.hhplus.be.server.coupon.presentation.mapper;

import kr.hhplus.be.server.coupon.application.dto.response.CouponResult;
import kr.hhplus.be.server.coupon.presentation.dto.reseponse.CouponResponse;

public class CouponResponseMapper {

    public static CouponResponse toResponse(CouponResult result) {

        return new CouponResponse(
                result.couponId(),
                result.couponName(),
                result.couponStatus(),
                result.discountAmount(),
                result.minimumPrice(),
                result.expireDate()
        );
    }
}
