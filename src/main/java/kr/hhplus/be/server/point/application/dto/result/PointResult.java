package kr.hhplus.be.server.point.application.dto.result;

import java.math.BigDecimal;

public record PointResult(

        Long userId,
        Long pointId,
        BigDecimal amount
){
}
