package kr.hhplus.be.server.coupon.application.port.in;

import kr.hhplus.be.server.coupon.application.dto.command.IssueCouponCommand;
import kr.hhplus.be.server.coupon.application.dto.command.UseCouponCommand;
import kr.hhplus.be.server.coupon.application.dto.command.ValidateCouponQuery;
import kr.hhplus.be.server.coupon.application.dto.result.CouponResult;
import kr.hhplus.be.server.order.application.dto.command.CouponCommand;

public interface CouponPort {

    CouponResult issueCoupon(IssueCouponCommand command);

    CouponResult useCoupon(UseCouponCommand command);

    boolean validateCoupon(ValidateCouponQuery query);
}
