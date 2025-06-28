package kr.hhplus.be.server.order.presentation.mapper;

import kr.hhplus.be.server.order.application.dto.command.PointCommand;
import kr.hhplus.be.server.order.presentation.dto.PointDto;

public class PointRequestMapper {

    public static PointCommand toCommand(PointDto request) {

        return new PointCommand(request.usePoint());
    }

}
