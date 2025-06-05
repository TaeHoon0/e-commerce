package kr.hhplus.be.server.coupon.infrastructure.persistence.jpa;

import kr.hhplus.be.server.coupon.domain.entity.CouponTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateJpaRepository extends JpaRepository<CouponTemplate, Long> {

}
