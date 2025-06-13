package kr.hhplus.be.server.order.presentation.dto;

import kr.hhplus.be.server.order.domain.payment.PaymentMethod;

public record PaymentDto (

    PaymentMethod method,

    String tid
){

}
