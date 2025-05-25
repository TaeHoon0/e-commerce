package kr.hhplus.be.server.point.presentation.dto.response;

import kr.hhplus.be.server.point.domain.PointChangedType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PointHistoryResponse(
    Long pointHistoryId,
    BigDecimal changedAmount,
    PointChangedType changedType,
    LocalDateTime regDate
) {
}
