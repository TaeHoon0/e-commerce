package kr.hhplus.be.server.point.presentation.dto.response;

import java.math.BigDecimal;

public record PointResponse(
    Long userId,
    BigDecimal amount
) {
}
