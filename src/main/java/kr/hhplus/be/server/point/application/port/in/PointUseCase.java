package kr.hhplus.be.server.point.application.port.in;

import kr.hhplus.be.server.point.domain.PointChangedType;
import kr.hhplus.be.server.point.presentation.dto.response.PointHistoryResponse;
import kr.hhplus.be.server.point.presentation.dto.response.PointResponse;

import java.math.BigDecimal;
import java.util.List;

public interface PointUseCase {

    /**
     * 포인트 충전
     */
    PointResponse chargePoint(Long userId, BigDecimal amount, PointChangedType changedType);

    /**
     * 포인트 사용
     */
    PointResponse usePoint(Long key, BigDecimal amount, PointChangedType changedType);

    /**
     * 포인트 조회
     */
    PointResponse getPoint(Long userId);

    /**
     * 포인트 이력 조회
     */
    List<PointHistoryResponse> getPointHistories(Long userId);
}
