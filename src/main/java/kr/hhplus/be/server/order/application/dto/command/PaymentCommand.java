package kr.hhplus.be.server.order.application.dto.command;

import java.math.BigDecimal;
import kr.hhplus.be.server.order.domain.payment.PG;
import kr.hhplus.be.server.order.domain.payment.PaymentMethod;
import lombok.Builder;

@Builder
public record PaymentCommand(

    String tid,
    PG pg,
    PaymentMethod method,
    BigDecimal finalPrice

) {

}
