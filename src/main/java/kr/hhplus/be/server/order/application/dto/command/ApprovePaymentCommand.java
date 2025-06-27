package kr.hhplus.be.server.order.application.dto.command;

public record ApprovePaymentCommand(

    long userId,
    long orderId,
    String tid

) {

}
