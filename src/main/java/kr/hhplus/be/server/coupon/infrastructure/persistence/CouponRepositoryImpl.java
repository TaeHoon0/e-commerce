package kr.hhplus.be.server.coupon.infrastructure.persistence;

import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;
import kr.hhplus.be.server.coupon.domain.repository.CouponCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponCommandRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void bulkInsert(UserCoupon coupon, int totalCount) {


    }
}
