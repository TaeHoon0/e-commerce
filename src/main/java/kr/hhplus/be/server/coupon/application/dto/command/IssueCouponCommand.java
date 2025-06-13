package kr.hhplus.be.server.coupon.application.dto.command;

public record IssueCouponCommand(

    Long userId,
    Long templateId

) {

}
