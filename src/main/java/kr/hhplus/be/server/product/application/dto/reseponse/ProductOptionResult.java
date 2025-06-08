package kr.hhplus.be.server.product.application.dto.reseponse;

import java.math.BigDecimal;

public record ProductOptionResult (

    long id,
    String optionName,
    int quantity,
    BigDecimal price

){

}
