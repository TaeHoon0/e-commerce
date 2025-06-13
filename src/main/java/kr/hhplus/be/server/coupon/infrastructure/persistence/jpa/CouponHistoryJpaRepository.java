package kr.hhplus.be.server.coupon.infrastructure.persistence.jpa;

import kr.hhplus.be.server.coupon.domain.entity.UserCouponHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponHistoryJpaRepository extends JpaRepository<UserCouponHistory, Long> {

}
