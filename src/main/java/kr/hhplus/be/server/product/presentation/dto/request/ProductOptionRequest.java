package kr.hhplus.be.server.product.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

public record ProductOptionRequest (

    @Positive
    Long optionId,

    @Length(max = 50)
    String optionName,

    @NotNull @PositiveOrZero
    Integer quantity,

    @NotNull @Positive
    BigDecimal price

){
}
