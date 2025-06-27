package kr.hhplus.be.server.order.application.usecase.in;

import kr.hhplus.be.server.order.application.dto.command.ApprovePaymentCommand;
import kr.hhplus.be.server.order.application.dto.command.CreatePaymentCommand;
import kr.hhplus.be.server.order.application.dto.result.ApprovePaymentResult;
import kr.hhplus.be.server.order.application.dto.result.CreatePaymentResult;
import kr.hhplus.be.server.order.application.mapper.PaymentResultMapper;
import kr.hhplus.be.server.order.application.port.in.PaymentPort;
import kr.hhplus.be.server.order.application.provider.PaymentProvider;
import kr.hhplus.be.server.order.domain.order.entity.Order;
import kr.hhplus.be.server.order.domain.payment.PaymentStrategy;
import kr.hhplus.be.server.order.domain.payment.entity.Payment;
import kr.hhplus.be.server.order.domain.repository.payment.PaymentCommandRepository;
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

    private final PaymentCommandRepository paymentCommandRepository;

    @Override
    @Transactional
    public CreatePaymentResult ready(CreatePaymentCommand command) {

        Order order = orderService.get(command.orderId());

        paymentService.validateCreatePayment(order, command.userId(), command.paymentCommand().finalPrice());

        Payment payment = Payment.create(
            command.userId(), command.paymentCommand().pg(), command.paymentCommand().method(), order);

        // PG 종류에 맞는 strategy 가져오기
        PaymentStrategy paymentStrategy = paymentProvider.getPaymentStrategy(command.paymentCommand().pg());

        paymentStrategy.ready(payment);

        paymentCommandRepository.save(payment);

        return PaymentResultMapper.toCreateResult(payment);
    }

    @Override
    @Transactional
    public ApprovePaymentResult approve(ApprovePaymentCommand command) {

        Order order = orderService.get(command.orderId());
        Payment payment = order.getPayment();

        paymentService.validateApprovePayment(order, command.userId(), command.tid());

        PaymentStrategy paymentStrategy = paymentProvider.getPaymentStrategy(payment.getPg());

        paymentStrategy.approve(payment);

        return PaymentResultMapper.toApproveResult(payment);
    }
}
