package kr.hhplus.be.server.coupon.domain.repository;

import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;

public interface CouponCommandRepository {

    void bulkInsert(UserCoupon userCoupon, int totalCount);
    UserCoupon issue(UserCoupon userCoupon);
}
