package kr.hhplus.be.server.coupon.domain.repository;

import java.util.Optional;
import kr.hhplus.be.server.coupon.domain.entity.CouponTemplate;

public interface TemplateQueryRepository {

    Optional<CouponTemplate> findByTemplateId(long templateId);
}
