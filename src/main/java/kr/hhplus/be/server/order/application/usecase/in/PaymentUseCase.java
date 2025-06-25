package kr.hhplus.be.server.order.application.usecase.in;

import kr.hhplus.be.server.order.application.dto.command.CreatePaymentCommand;
import kr.hhplus.be.server.order.application.dto.command.PaymentCommand;
import kr.hhplus.be.server.order.application.dto.result.CreatePaymentResult;
import kr.hhplus.be.server.order.application.port.in.PaymentPort;
import kr.hhplus.be.server.order.domain.order.entity.Order;
import kr.hhplus.be.server.order.domain.payment.entity.Payment;
import kr.hhplus.be.server.order.domain.payment.event.PaymentCreatedEvent;
import kr.hhplus.be.server.order.domain.service.OrderService;
import kr.hhplus.be.server.order.domain.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentUseCase implements PaymentPort {

    private final OrderService orderService;
    private final PaymentService paymentService;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public CreatePaymentResult ready(CreatePaymentCommand command) {

        PaymentCommand paymentCommand = command.paymentCommand();

        // TODO 결제 검증, 결제 생성, 결제 준비 요청-> evnet로 transaction 밖에서, out box..?
        Order order = orderService.get(command.orderId());

        paymentService.validatePayment(order, command.userId(), command.paymentCommand().finalPrice());

        Payment payment = Payment.create(
            command.userId(), command.paymentCommand().pg(), command.paymentCommand().method(), order);

        eventPublisher.publishEvent(new PaymentCreatedEvent(payment));

        return null;
    }
}
