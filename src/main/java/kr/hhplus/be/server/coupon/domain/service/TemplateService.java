package kr.hhplus.be.server.coupon.domain.service;

import kr.hhplus.be.server.coupon.domain.entity.CouponTemplate;
import kr.hhplus.be.server.coupon.domain.exception.CouponErrorCode;
import kr.hhplus.be.server.coupon.domain.exception.CouponException;
import kr.hhplus.be.server.coupon.domain.repository.TemplateCommandRepository;
import kr.hhplus.be.server.coupon.domain.repository.TemplateQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateQueryRepository templateQueryRepository;
    private final TemplateCommandRepository templateCommandRepository;

    public CouponTemplate get(long templateId) {

        return templateQueryRepository.findByTemplateId(templateId)
            .orElseThrow(() -> new CouponException(CouponErrorCode.COUPON_TEMPLATE_NOT_FOUND));
    }

    public void decreaseRemainCount(CouponTemplate template) {

        if (template.getRemainCount() < 1)
            throw new CouponException(CouponErrorCode.COUPON_OUT_OF_STOCK);

        // 원자적 감소를 사용하여 template 남은 수량과 발급 수량 정합성 유지
        long affected = templateCommandRepository.decreaseRemainCount(template.getId());

        if (affected == 0)
            throw new CouponException(CouponErrorCode.COUPON_OUT_OF_STOCK);
    }
}
