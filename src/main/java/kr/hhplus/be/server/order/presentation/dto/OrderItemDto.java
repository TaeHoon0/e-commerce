package kr.hhplus.be.server.order.presentation.dto;

import java.math.BigDecimal;

public record OrderItemDto(

        Long itemId,
        String itemName,
        Integer quantity,
        BigDecimal price
) {
}
