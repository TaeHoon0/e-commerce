package kr.hhplus.be.server.point.presentation.dto.request;

import jakarta.validation.constraints.Positive;
import kr.hhplus.be.server.point.domain.PointChangedType;

import java.math.BigDecimal;

public record PointRequest(

        @Positive
        BigDecimal amount,

        PointChangedType changedType

) {
}
