package kr.hhplus.be.server.order.application.dto.command;

public record CreatePaymentCommand(

    Long userId,
    Long orderId,
    PaymentCommand paymentCommand

) {

}
