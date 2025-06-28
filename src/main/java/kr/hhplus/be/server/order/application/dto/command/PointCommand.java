package kr.hhplus.be.server.order.application.dto.command;

import java.math.BigDecimal;

public record PointCommand (

    BigDecimal amount
){

}
