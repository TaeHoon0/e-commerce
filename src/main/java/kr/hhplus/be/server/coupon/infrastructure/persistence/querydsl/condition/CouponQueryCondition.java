package kr.hhplus.be.server.coupon.infrastructure.persistence.querydsl.condition;

import kr.hhplus.be.server.coupon.domain.CouponStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CouponQueryCondition {

    Long couponId;
    Long userId;
    CouponStatus status;

}
