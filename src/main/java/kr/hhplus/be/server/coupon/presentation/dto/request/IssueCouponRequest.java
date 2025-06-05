package kr.hhplus.be.server.coupon.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record IssueCouponRequest(

    @Positive @NotNull Long templateId
) {

}
