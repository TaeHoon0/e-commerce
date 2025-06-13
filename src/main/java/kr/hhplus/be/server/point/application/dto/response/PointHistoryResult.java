package kr.hhplus.be.server.point.application.dto.response;

import kr.hhplus.be.server.point.domain.PointChangeType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PointHistoryResult(

        Long pointHistoryId,
        BigDecimal changedAmount,
        PointChangeType changedType,
        LocalDateTime regDate

) {
}
