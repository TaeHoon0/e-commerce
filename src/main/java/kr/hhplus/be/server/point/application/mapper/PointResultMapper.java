package kr.hhplus.be.server.point.application.mapper;

import kr.hhplus.be.server.point.application.dto.response.PointResult;
import kr.hhplus.be.server.point.domain.entity.Point;

public class PointResultMapper {

    public static PointResult toResult(Point entity) {

        return new PointResult(
                entity.getUserId(),
                entity.getId(),
                entity.getAmount()
        );
    }

}
