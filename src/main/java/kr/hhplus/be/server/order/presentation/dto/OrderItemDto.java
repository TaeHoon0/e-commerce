package kr.hhplus.be.server.order.presentation.dto;

import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record OrderItemDto(

        @Positive
        Long productId,

        @Positive
        Long productOptionId,

        @Positive
        Integer quantity,

        @Positive
        BigDecimal price
) {
}
