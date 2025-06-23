package kr.hhplus.be.server.order.presentation.mapper;

import kr.hhplus.be.server.order.application.dto.command.CouponCommand;
import kr.hhplus.be.server.order.application.dto.command.CreateOrderCommand;
import kr.hhplus.be.server.order.presentation.dto.request.CreateOrderRequest;

public class OrderRequestMapper {

    public static CreateOrderCommand toCreateCommand(CreateOrderRequest request) {

        if (request.coupon() != null) {
            CouponCommand couponCommand = CouponRequestMapper.toCommand(request.coupon());
        }

        if (request.)


        return
    }
}
