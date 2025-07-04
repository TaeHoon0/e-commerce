package kr.hhplus.be.server.coupon.infrastructure.persistence;


import java.util.Optional;
import kr.hhplus.be.server.coupon.domain.CouponStatus;
import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;
import kr.hhplus.be.server.coupon.domain.repository.CouponCommandRepository;
import kr.hhplus.be.server.coupon.domain.repository.CouponQueryRepository;
import kr.hhplus.be.server.coupon.infrastructure.persistence.jdbc.CouponJdbcRepository;
import kr.hhplus.be.server.coupon.infrastructure.persistence.jpa.CouponJpaRepository;
import kr.hhplus.be.server.coupon.infrastructure.persistence.querydsl.CouponQueryDslRepository;
import kr.hhplus.be.server.coupon.infrastructure.persistence.querydsl.condition.CouponQueryCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponCommandRepository, CouponQueryRepository {

    private final CouponJdbcRepository couponJdbcRepository;
    private final CouponQueryDslRepository couponQueryDslRepository;

    @Override
    public void bulkInsert(UserCoupon userCoupon, int count) {

        couponJdbcRepository.bulkInsert(userCoupon, count);
    }

    @Override
    public Optional<UserCoupon> findByTemplateIdWithLock(Long templateId) {

        return couponQueryDslRepository.findByTemplateIdWithLock(templateId);
    }

    @Override
    public Optional<UserCoupon> findByTemplateIdAndUserId(Long templateId, Long userId) {

        return couponQueryDslRepository.findByTemplateIdAndUserId(templateId, userId);
    }

    @Override
    public Optional<UserCoupon> findByCouponIdWithLock(Long couponId) {

        return couponQueryDslRepository.findByCouponIdWithLock(couponId);
    }

    @Override
    public Optional<UserCoupon> findByCouponIdAndUserIdAndStatus(Long couponId, Long userId, CouponStatus status) {

        CouponQueryCondition condition = CouponQueryCondition.builder()
            .couponId(couponId)
            .userId(userId)
            .status(status)
            .build();

        return couponQueryDslRepository.findByCondition(condition);
    }
}
