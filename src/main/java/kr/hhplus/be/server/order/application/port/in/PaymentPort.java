package kr.hhplus.be.server.order.application.port.in;

import kr.hhplus.be.server.order.application.dto.command.ApprovePaymentCommand;
import kr.hhplus.be.server.order.application.dto.command.CreatePaymentCommand;
import kr.hhplus.be.server.order.application.dto.result.ApprovePaymentResult;
import kr.hhplus.be.server.order.application.dto.result.CreatePaymentResult;

public interface PaymentPort {

    /**
     * 결제 준비
     */
    CreatePaymentResult ready(CreatePaymentCommand command);

    /**
     * 결제 승인
     */
    ApprovePaymentResult approve(ApprovePaymentCommand command);

}
