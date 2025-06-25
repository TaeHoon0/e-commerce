package kr.hhplus.be.server.order.application.dto.result;

public record CreatePaymentResult (

    Long orderId,
    PaymentResult payment

) {

}
