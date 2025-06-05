package kr.hhplus.be.server.product.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

public record ProductOptionRequest (

        List<OptionRequest> options

){

    public record OptionRequest(
            @Length(max = 50) String optionName,
            @NotNull @PositiveOrZero Integer quantity,
            @NotNull @PositiveOrZero BigDecimal price
    ) {}
}
