package kr.hhplus.be.server.point.application.mapper;

import kr.hhplus.be.server.point.domain.entity.Point;

import kr.hhplus.be.server.point.domain.entity.PointHistory;
import kr.hhplus.be.server.point.presentation.dto.response.PointHistoryResponse;
import kr.hhplus.be.server.point.presentation.dto.response.PointResponse;

public class PointMapper {

    public static PointResponse toDto(Point point) {

        return new PointResponse(
                point.getUserId(),
                point.getAmount()
        );
    }

    public static PointHistoryResponse toDto(PointHistory pointHistory) {

        return new PointHistoryResponse(
                pointHistory.getId(),
                pointHistory.getChangedAmount(),
                pointHistory.getChangedType(),
                pointHistory.getCreatedDate()
        );
    }
}
