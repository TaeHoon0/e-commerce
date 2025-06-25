package kr.hhplus.be.server.order.infrastructure.payment;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import kr.hhplus.be.server.order.application.provider.PaymentProvider;
import kr.hhplus.be.server.order.domain.exception.OrderErrorCode;
import kr.hhplus.be.server.order.domain.exception.OrderException;
import kr.hhplus.be.server.order.domain.payment.PG;
import kr.hhplus.be.server.order.domain.payment.PaymentStrategy;
import org.springframework.stereotype.Component;

@Component
public class PaymentProviderImpl implements PaymentProvider {

    private final Map<PG, PaymentStrategy> strategyMap;

    /**
     * Bean에 등록된 모듈 들고와서, method 에 맞게 매핑
     */
    public PaymentProviderImpl(List<PaymentStrategy> strategies) {
        this.strategyMap = strategies.stream()
            .collect(Collectors.toMap(
                PaymentStrategy::getPaymentMethod,
                strategy -> strategy
            ));
    }

    @Override
    public PaymentStrategy getPaymentStrategy(PG pg) {

        return Optional.ofNullable(strategyMap.get(pg))
            .orElseThrow(() -> new OrderException(OrderErrorCode.PAYMENT_METHOD_NOT_SUPPORTED));
    }
}
