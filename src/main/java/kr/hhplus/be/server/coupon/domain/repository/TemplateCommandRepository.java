package kr.hhplus.be.server.coupon.domain.repository;

import kr.hhplus.be.server.coupon.domain.entity.CouponTemplate;

public interface TemplateCommandRepository {

    CouponTemplate save(CouponTemplate couponTemplate);
}
