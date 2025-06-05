package kr.hhplus.be.server.coupon.presentation.mapper;

import kr.hhplus.be.server.coupon.application.dto.request.IssueCouponCommand;
import kr.hhplus.be.server.coupon.presentation.dto.request.IssueCouponRequest;

public class CouponRequestMapper {


    public static IssueCouponCommand toIssueCommand(Long userId, IssueCouponRequest request) {

        return new IssueCouponCommand(
            userId,
            request.templateId()
        );
    }
}
