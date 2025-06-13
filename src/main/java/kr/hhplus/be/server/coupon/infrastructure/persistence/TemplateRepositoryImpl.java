package kr.hhplus.be.server.coupon.infrastructure.persistence;

import java.util.Optional;
import kr.hhplus.be.server.coupon.domain.entity.CouponTemplate;
import kr.hhplus.be.server.coupon.domain.repository.TemplateCommandRepository;
import kr.hhplus.be.server.coupon.domain.repository.TemplateQueryRepository;
import kr.hhplus.be.server.coupon.infrastructure.persistence.jpa.TemplateJpaRepository;
import kr.hhplus.be.server.coupon.infrastructure.persistence.querydsl.TemplateQueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TemplateRepositoryImpl implements TemplateCommandRepository, TemplateQueryRepository {

    private final TemplateJpaRepository templateJpaRepository;
    private final TemplateQueryDslRepository templateQueryDslRepository;

    @Override
    public CouponTemplate save(CouponTemplate couponTemplate) {

        return templateJpaRepository.save(couponTemplate);
    }

    @Override
    public Optional<CouponTemplate> findByTemplateId(long templateId) {

        return templateJpaRepository.findById(templateId);
    }

    @Override
    public long decreaseRemainCount(long templateId) {

        return templateQueryDslRepository.decreaseRemainCount(templateId);
    }
}
