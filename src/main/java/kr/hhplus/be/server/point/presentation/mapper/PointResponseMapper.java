package kr.hhplus.be.server.point.presentation.mapper;

import kr.hhplus.be.server.point.application.dto.response.PointResult;
import kr.hhplus.be.server.point.presentation.dto.response.PointResponse;

public class PointResponseMapper {

    public static PointResponse toResponse(PointResult result) {

        return new PointResponse(
            result.userId(),
            result.pointId(),
            result.amount()
        );
    }
}
