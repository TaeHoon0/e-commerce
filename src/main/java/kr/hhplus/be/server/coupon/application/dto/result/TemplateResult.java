package kr.hhplus.be.server.coupon.application.dto.result;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TemplateResult (

    long templateId,
    String templateName,
    int totalCount,
    int remainCount,
    BigDecimal discountAmount,
    BigDecimal minimumPrice,
    LocalDateTime expireTime

) {
}
