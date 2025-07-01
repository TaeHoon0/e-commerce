package kr.hhplus.be.server.order.application.mapper;

import kr.hhplus.be.server.order.application.dto.result.ApprovePaymentResult;
import kr.hhplus.be.server.order.application.dto.result.CreatePaymentResult;
import kr.hhplus.be.server.order.application.dto.result.PaymentResult;
import kr.hhplus.be.server.order.domain.payment.entity.Payment;

public class PaymentResultMapper {

    public static CreatePaymentResult toCreateResult(Payment payment) {

        return new CreatePaymentResult(
            payment.getOrder().getId(),
            new PaymentResult(
                payment.getTid(),
                payment.getPg(),
                payment.getMethod(),
                payment.getFinalPrice()
            )
        );
    }

    public static ApprovePaymentResult toApproveResult(Payment payment) {

        return new ApprovePaymentResult(
            payment.getOrder().getId()
        );
    }
}
