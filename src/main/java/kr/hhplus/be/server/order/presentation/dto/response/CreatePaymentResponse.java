package kr.hhplus.be.server.order.presentation.dto.response;

import kr.hhplus.be.server.order.presentation.dto.PaymentDto;

public record CreatePaymentResponse (

    Long orderId,
    PaymentDto payment

){

}
