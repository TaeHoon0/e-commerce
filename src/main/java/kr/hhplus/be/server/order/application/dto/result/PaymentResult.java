package kr.hhplus.be.server.order.application.dto.result;

import java.math.BigDecimal;
import kr.hhplus.be.server.order.domain.payment.PG;
import kr.hhplus.be.server.order.domain.payment.PaymentMethod;

public record PaymentResult(

    String tid,
    PG pg,
    PaymentMethod method,
    BigDecimal finalPrice

) {

}
