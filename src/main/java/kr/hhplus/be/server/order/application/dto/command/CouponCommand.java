package kr.hhplus.be.server.order.application.dto.command;

import jakarta.validation.constraints.Positive;

public record CouponCommand (

    @Positive
    Long couponId
) {

}
