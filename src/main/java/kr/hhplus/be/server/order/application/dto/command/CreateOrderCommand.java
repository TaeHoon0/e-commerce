package kr.hhplus.be.server.order.application.dto.command;

import java.util.List;
import kr.hhplus.be.server.order.domain.payment.PaymentMethod;

public record CreateOrderCommand (

    Long userId,
    String idempotencyKey,
    PaymentMethod paymentMethod,
    CouponCommand couponCommand,
    PointCommand pointCommand,
    List<ProductCommand> productCommands

) {
}
