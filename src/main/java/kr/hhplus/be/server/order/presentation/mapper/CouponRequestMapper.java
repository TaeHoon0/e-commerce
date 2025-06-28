package kr.hhplus.be.server.order.presentation.mapper;

import kr.hhplus.be.server.order.application.dto.command.CouponCommand;
import kr.hhplus.be.server.order.presentation.dto.CouponDto;

public class CouponRequestMapper {

    public static CouponCommand toCommand(CouponDto couponDto) {

        return new CouponCommand(couponDto.couponId(), couponDto.discountAmount());
    }
}
