package kr.hhplus.be.server.point.presentation.mapper;

import kr.hhplus.be.server.point.application.dto.result.PointHistoryResult;
import kr.hhplus.be.server.point.presentation.dto.response.PointHistoryResponse;

public class PointHistoryResponseMapper {

    public static PointHistoryResponse toResponse(PointHistoryResult result) {

        return new PointHistoryResponse(
            result.pointHistoryId(),
            result.changedAmount(),
            result.changedType(),
            result.regDate()
        );
    }
}
