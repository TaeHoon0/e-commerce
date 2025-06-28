package kr.hhplus.be.server.order.application.dto.result;

import kr.hhplus.be.server.order.domain.payment.PaymentStatus;

public record ApprovePaymentResult (

    long orderId,
    PaymentStatus paymentStatus

){

}
