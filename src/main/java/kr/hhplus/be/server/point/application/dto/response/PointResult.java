package kr.hhplus.be.server.point.application.dto.response;

import java.math.BigDecimal;

public record PointResult(

        Long userId,
        Long pointId,
        BigDecimal amount
){
}
