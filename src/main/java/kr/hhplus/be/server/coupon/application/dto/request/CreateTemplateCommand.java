package kr.hhplus.be.server.coupon.application.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateTemplateCommand (

    String templateName,
    Integer totalCount,
    BigDecimal discountAmount,
    BigDecimal minimumPrice,
    LocalDateTime expireDate
){

}
