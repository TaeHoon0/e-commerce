package kr.hhplus.be.server.coupon.infrastructure.persistence;

import java.util.Optional;
import kr.hhplus.be.server.coupon.domain.entity.CouponTemplate;
import kr.hhplus.be.server.coupon.domain.repository.TemplateCommandRepository;
import kr.hhplus.be.server.coupon.domain.repository.TemplateQueryRepository;
import kr.hhplus.be.server.coupon.infrastructure.persistence.jpa.TemplateJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TemplateRepositoryImpl implements TemplateCommandRepository, TemplateQueryRepository {

    private final TemplateJpaRepository templateJpaRepository;

    @Override
    public CouponTemplate save(CouponTemplate couponTemplate) {

        return templateJpaRepository.save(couponTemplate);
    }

    @Override
    public Optional<CouponTemplate> findByTemplateId(Long templateId) {

        return templateJpaRepository.findById(templateId);
    }
}
