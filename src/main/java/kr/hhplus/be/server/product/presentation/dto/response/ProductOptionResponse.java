package kr.hhplus.be.server.product.presentation.dto.response;

import java.math.BigDecimal;

public record ProductOptionResponse (

        Long id,
        String optionName,
        int quantity,
        BigDecimal price

){
}
