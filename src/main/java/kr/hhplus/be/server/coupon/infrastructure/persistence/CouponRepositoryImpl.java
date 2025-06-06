package kr.hhplus.be.server.coupon.infrastructure.persistence;


import java.util.Optional;
import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;
import kr.hhplus.be.server.coupon.domain.repository.CouponCommandRepository;
import kr.hhplus.be.server.coupon.domain.repository.CouponQueryRepository;
import kr.hhplus.be.server.coupon.infrastructure.persistence.jdbc.CouponJdbcRepository;
import kr.hhplus.be.server.coupon.infrastructure.persistence.querydsl.CouponQueryDslRepository;
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
    public UserCoupon issue(UserCoupon userCoupon) {
        return null;
    }

    @Override
    public Optional<UserCoupon> findByTemplateIdWithLock(Long templateId) {

        return couponQueryDslRepository.findByTemplateIdWithLock(templateId);
    }
}
