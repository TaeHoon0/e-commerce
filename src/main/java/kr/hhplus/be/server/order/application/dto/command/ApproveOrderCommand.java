package kr.hhplus.be.server.order.application.dto.command;

public record ApproveOrderCommand (

    long userId,
    long orderId,
    String tid

) {

}
