package kr.hhplus.be.server.coupon.application.port.in;

import kr.hhplus.be.server.coupon.application.dto.request.IssueCouponCommand;
import kr.hhplus.be.server.coupon.application.dto.request.UseCouponCommand;
import kr.hhplus.be.server.coupon.application.dto.response.CouponResult;

public interface CouponPort {

    CouponResult issueCoupon(IssueCouponCommand command);

    CouponResult useCoupon(UseCouponCommand command);
}
