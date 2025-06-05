package kr.hhplus.be.server.point.application.mapper;

import kr.hhplus.be.server.point.application.dto.response.PointHistoryResult;
import kr.hhplus.be.server.point.domain.entity.PointHistory;

public class PointHistoryResultMapper {

    public static PointHistoryResult toResult(PointHistory entity ) {

        return new PointHistoryResult(
                entity.getId(),
                entity.getChangedAmount(),
                entity.getChangedType(),
                entity.getCreatedDate()
        );
    }
}
