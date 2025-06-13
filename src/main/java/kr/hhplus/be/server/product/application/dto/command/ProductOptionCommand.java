package kr.hhplus.be.server.product.application.dto.command;

import java.math.BigDecimal;

public record ProductOptionCommand (

    Long optionId,
    String optionName,
    int quantity,
    BigDecimal price
){
}
