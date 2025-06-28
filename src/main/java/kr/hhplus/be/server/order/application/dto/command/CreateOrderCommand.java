package kr.hhplus.be.server.order.application.dto.command;

import java.util.List;

public record CreateOrderCommand (

    Long userId,
    String idempotencyKey,
    CouponCommand couponCommand,
    PointCommand pointCommand,
    List<ProductCommand> productCommands

) {
}
