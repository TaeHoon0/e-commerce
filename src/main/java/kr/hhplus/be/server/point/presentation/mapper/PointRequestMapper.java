package kr.hhplus.be.server.point.presentation.mapper;

import kr.hhplus.be.server.point.application.dto.request.ChargePointCommand;
import kr.hhplus.be.server.point.application.dto.request.UsePointCommand;
import kr.hhplus.be.server.point.presentation.dto.request.PointRequest;


public class PointRequestMapper {

    public static ChargePointCommand toChargeCommand(Long userId, PointRequest request) {

        return new ChargePointCommand(
                userId,
                request.amount(),
                request.changedType()
        );
    }

    public static UsePointCommand toUseCommand(Long userId, PointRequest request) {

        return new UsePointCommand(
                userId,
                request.amount(),
                request.changedType()
        );
    }
}