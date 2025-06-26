package kr.hhplus.be.server.order.presentation.mapper;

import kr.hhplus.be.server.order.application.dto.command.CreatePaymentCommand;
import kr.hhplus.be.server.order.application.dto.command.PaymentCommand;
import kr.hhplus.be.server.order.presentation.dto.request.CreatePaymentRequest;

public class PaymentRequestMapper {

    public static CreatePaymentCommand toCommand(
        long userId, CreatePaymentRequest request
    ) {

        return new CreatePaymentCommand(
            userId,
            request.orderId(),
            PaymentCommand.builder()
                .pg(request.payment().pg())
                .method(request.payment().paymentMethod())
                .finalPrice(request.payment().finalPrice())
                .build()
        );
    }
}
