package kr.hhplus.be.server.coupon.domain.repository;

import java.util.Optional;
import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;

public interface CouponQueryRepository {

    Optional<UserCoupon> findByTemplateIdWithLock(Long templateId);
}
