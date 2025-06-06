package kr.hhplus.be.server.coupon.domain.repository;

import kr.hhplus.be.server.coupon.domain.entity.UserCouponHistory;

public interface CouponHistoryCommandRepository {

    void save(UserCouponHistory couponHistory);
}
