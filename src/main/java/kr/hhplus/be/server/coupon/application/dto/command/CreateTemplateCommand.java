package kr.hhplus.be.server.coupon.application.dto.command;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import kr.hhplus.be.server.coupon.domain.CouponType;

public record CreateTemplateCommand (

    String templateName,
    Integer totalCount,
    BigDecimal discountAmount,
    BigDecimal minimumPrice,
    LocalDateTime expireDate,
    CouponType type

){

}
