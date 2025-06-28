package kr.hhplus.be.server.order.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kr.hhplus.be.server.order.presentation.dto.PaymentDto;

public record ApprovePaymentRequest (

    @NotNull
    @Positive
    long orderId,

    @NotNull
    PaymentDto payment
){

}
