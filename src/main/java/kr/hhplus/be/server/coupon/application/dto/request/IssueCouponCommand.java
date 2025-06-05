package kr.hhplus.be.server.coupon.application.dto.request;

public record IssueCouponCommand(

    Long userId,
    Long templateId

) {

}
