package kr.hhplus.be.server.coupon.application.mapper;

import kr.hhplus.be.server.coupon.application.dto.result.TemplateResult;
import kr.hhplus.be.server.coupon.domain.entity.CouponTemplate;

public class TemplateResultMapper {

    public static TemplateResult toResult(CouponTemplate entity) {

        return new TemplateResult(
            entity.getId(),
            entity.getName(),
            entity.getTotalCount(),
            entity.getRemainCount(),
            entity.getDiscountAmount(),
            entity.getMinimumPrice(),
            entity.getExpireDate()
        );
    }
}
