package kr.hhplus.be.server.order.presentation.mapper;

import kr.hhplus.be.server.order.application.dto.result.CreatePaymentResult;
import kr.hhplus.be.server.order.presentation.dto.PaymentDto;
import kr.hhplus.be.server.order.presentation.dto.response.CreatePaymentResponse;

public class PaymentResponseMapper {

    public static CreatePaymentResponse toResponse(CreatePaymentResult result) {

        return new CreatePaymentResponse(
            result.orderId(),
            new PaymentDto(
                result.payment().pg(),
                result.payment().method(),
                result.payment().tid(),
                result.payment().finalPrice()
            )
        );
    }
}
