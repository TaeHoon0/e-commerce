package kr.hhplus.be.server.point.application.dto.command;

import kr.hhplus.be.server.point.domain.PointChangeType;

import java.math.BigDecimal;

public record UsePointCommand(

        Long userId,
        BigDecimal amount,
        PointChangeType type

) {
}
