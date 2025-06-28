package kr.hhplus.be.server.order.presentation.dto;

import java.math.BigDecimal;
import kr.hhplus.be.server.order.domain.payment.PG;
import kr.hhplus.be.server.order.domain.payment.PaymentMethod;

public record PaymentDto (

    PG pg,

    PaymentMethod paymentMethod,

    String tid,

    BigDecimal finalPrice
){

}
