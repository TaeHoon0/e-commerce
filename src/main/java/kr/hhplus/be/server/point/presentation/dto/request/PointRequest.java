package kr.hhplus.be.server.point.presentation.dto.request;

import jakarta.validation.constraints.Positive;
import kr.hhplus.be.server.point.domain.PointChangeType;

import java.math.BigDecimal;

public record PointRequest(

        @Positive
        BigDecimal amount,

        PointChangeType changedType

) {
}
