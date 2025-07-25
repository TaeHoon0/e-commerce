package kr.hhplus.be.server.coupon.presentation.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import kr.hhplus.be.server.coupon.domain.CouponType;
import org.hibernate.validator.constraints.Length;

public record TemplateRequest (

    @Positive
    Long templateId,

    @Length(max = 30) @NotNull
    String templateName,

    @Positive @NotNull
    Integer totalCount,

    @NotNull
    CouponType type,

    @Positive @NotNull
    BigDecimal discountAmount,

    @Positive
    BigDecimal minimumPrice,

    @Future
    LocalDateTime expireDate
) {

}
