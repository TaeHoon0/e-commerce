package kr.hhplus.be.server.order.presentation.dto;

import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record PointDto (

    @Positive
    BigDecimal usePoint,

    @Positive
    BigDecimal chargePoint
){

}
