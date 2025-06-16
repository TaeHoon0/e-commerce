package kr.hhplus.be.server.order.application.dto.command;

import java.math.BigDecimal;

public record ProductCommand(

    Long productId,
    Long productOptionId,
    BigDecimal price,
    int quantity

) {

}
