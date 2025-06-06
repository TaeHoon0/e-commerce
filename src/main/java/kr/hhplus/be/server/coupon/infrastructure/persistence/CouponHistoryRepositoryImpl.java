package kr.hhplus.be.server.coupon.infrastructure.persistence;

import kr.hhplus.be.server.coupon.domain.entity.UserCouponHistory;
import kr.hhplus.be.server.coupon.domain.repository.CouponHistoryCommandRepository;
import kr.hhplus.be.server.coupon.infrastructure.persistence.jpa.CouponHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CouponHistoryRepositoryImpl implements CouponHistoryCommandRepository {

    private final CouponHistoryJpaRepository jpaRepository;

    public void save(UserCouponHistory history) {

        jpaRepository.save(history);
    }
}
