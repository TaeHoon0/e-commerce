package kr.hhplus.be.server.point.application.dto.request;

import kr.hhplus.be.server.point.domain.PointChangeType;

import java.math.BigDecimal;

public record ChargePointCommand(

        Long userId,
        BigDecimal amount,
        PointChangeType type

) {}
