package kr.hhplus.be.server.coupon.domain.repository;

import java.util.Optional;
import kr.hhplus.be.server.coupon.domain.CouponStatus;
import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;

public interface CouponQueryRepository {

    Optional<UserCoupon> findByTemplateIdWithLock(Long templateId);

    Optional<UserCoupon> findByTemplateIdAndUserId(Long templateId, Long userId);

    Optional<UserCoupon> findByCouponIdWithLock(Long couponId);

    Optional<UserCoupon> findByCouponIdAndUserIdAndStatus(Long couponId, Long userId, CouponStatus status);
}
