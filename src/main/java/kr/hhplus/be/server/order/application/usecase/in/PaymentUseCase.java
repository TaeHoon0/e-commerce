package kr.hhplus.be.server.order.application.usecase.in;

import kr.hhplus.be.server.order.application.dto.command.CreatePaymentCommand;
import kr.hhplus.be.server.order.application.dto.result.CreatePaymentResult;
import kr.hhplus.be.server.order.application.mapper.PaymentResultMapper;
import kr.hhplus.be.server.order.application.port.in.PaymentPort;
import kr.hhplus.be.server.order.application.provider.PaymentProvider;
import kr.hhplus.be.server.order.domain.order.entity.Order;
import kr.hhplus.be.server.order.domain.payment.PaymentStrategy;
import kr.hhplus.be.server.order.domain.payment.entity.Payment;
import kr.hhplus.be.server.order.domain.service.OrderService;
import kr.hhplus.be.server.order.domain.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentUseCase implements PaymentPort {

    private final OrderService orderService;
    private final PaymentService paymentService;

    private final PaymentProvider paymentProvider;

    @Override
    @Transactional
    public CreatePaymentResult ready(CreatePaymentCommand command) {

        // TODO 결제 검증, 결제 생성, 결제 준비 요청-> evnet로 transaction 밖에서, out box..?
        Order order = orderService.get(command.orderId());

        paymentService.validatePayment(order, command.userId(), command.paymentCommand().finalPrice());

        Payment payment = Payment.create(
            command.userId(), command.paymentCommand().pg(), command.paymentCommand().method(), order);

        // PG 종류에 맞는 strategy 가져오기
        PaymentStrategy paymentStrategy = paymentProvider.getPaymentStrategy(command.paymentCommand().pg());

        paymentStrategy.ready(payment);

        return PaymentResultMapper.toResult(payment);
    }
}
