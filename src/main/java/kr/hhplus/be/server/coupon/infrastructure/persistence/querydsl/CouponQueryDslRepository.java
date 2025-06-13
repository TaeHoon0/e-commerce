package kr.hhplus.be.server.coupon.infrastructure.persistence.querydsl;

import static kr.hhplus.be.server.coupon.domain.entity.QUserCoupon.userCoupon;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import kr.hhplus.be.server.coupon.domain.CouponStatus;
import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;
import kr.hhplus.be.server.coupon.infrastructure.persistence.querydsl.condition.CouponQueryCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<UserCoupon> findByTemplateIdWithLock(Long templateId) {

        JPAQuery<UserCoupon> query =  queryFactory.selectFrom(userCoupon)
            .where(userCoupon.couponTemplate.id.eq(templateId)
              .and(userCoupon.status.eq(CouponStatus.UNASSIGNED)))
            .orderBy(userCoupon.id.asc());

        query.setLockMode(LockModeType.PESSIMISTIC_WRITE);

        query.setHint("hibernate.lock.skip_locked", true);
        query.setHint("javax.persistence.lock.timeout", 3);

        return Optional.ofNullable(query.fetchFirst());
    }

    public Optional<UserCoupon> findByTemplateIdAndUserId(Long templateId, Long userId) {

        return Optional.ofNullable(
            queryFactory.selectFrom(userCoupon)
                        .where(userCoupon.couponTemplate.id.eq(templateId)
                          .and(userCoupon.userId.eq(userId)))
                        .fetchFirst()
        );
    }

    public Optional<UserCoupon> findByCouponIdWithLock(Long couponId) {

        JPAQuery<UserCoupon> query =  queryFactory.selectFrom(userCoupon)
                .where(userCoupon.id.eq(couponId)
                  .and(userCoupon.status.eq(CouponStatus.AVAILABLE)));

        query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
        query.setHint("javax.persistence.lock.timeout", 3);

        return Optional.ofNullable(query.fetchFirst());
    }

    public Optional<UserCoupon> findByCondition(CouponQueryCondition condition) {

        return Optional.ofNullable(
            queryFactory.selectFrom(userCoupon)
                .where(userCoupon.id.eq(condition.getCouponId())
                    .and(userCoupon.userId.eq(condition.getUserId()))
                    .and(userCoupon.status.eq(condition.getStatus())))
                .fetchFirst()
        );
    }
}
