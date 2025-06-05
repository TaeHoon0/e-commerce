package kr.hhplus.be.server.point.presentation.dto.response;

import kr.hhplus.be.server.point.domain.PointChangeType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PointHistoryResponse(

    Long pointHistoryId,
    BigDecimal changedAmount,
    PointChangeType changedType,
    LocalDateTime regDate

) {
}
