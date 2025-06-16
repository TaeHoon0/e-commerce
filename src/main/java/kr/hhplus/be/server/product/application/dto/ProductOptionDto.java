package kr.hhplus.be.server.product.application.dto;

import java.math.BigDecimal;

public record ProductOptionDto(

    Long optionId,
    String optionName,
    int quantity,
    BigDecimal price
){
}
