package kr.hhplus.be.server.point.application.port.in;

import kr.hhplus.be.server.point.application.dto.command.ChargePointCommand;
import kr.hhplus.be.server.point.application.dto.command.UsePointCommand;
import kr.hhplus.be.server.point.application.dto.result.PointHistoryResult;
import kr.hhplus.be.server.point.application.dto.result.PointResult;

import java.util.List;

public interface PointPort {

    /**
     * 포인트 충전
     */
    PointResult chargePoint(ChargePointCommand command);

    /**
     * 포인트 사용
     */
    PointResult usePoint(UsePointCommand command);

    /**
     * 포인트 조회
     */
    PointResult getPoint(Long userId);

    /**
     * 포인트 이력 조회
     */
    List<PointHistoryResult> getPointHistories(Long userId);
}
