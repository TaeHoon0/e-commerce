package kr.hhplus.be.server.point.application.usecase.in;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.point.application.Mapper.PointMapper;
import kr.hhplus.be.server.point.application.port.in.PointUseCase;
import kr.hhplus.be.server.point.domain.PointChangedType;
import kr.hhplus.be.server.point.domain.entity.Point;
import kr.hhplus.be.server.point.domain.entity.PointHistory;
import kr.hhplus.be.server.point.domain.exception.ErrorCode;
import kr.hhplus.be.server.point.domain.exception.PointException;
import kr.hhplus.be.server.point.domain.repository.PointHistoryRepository;
import kr.hhplus.be.server.point.domain.repository.PointRepository;
import kr.hhplus.be.server.point.domain.service.PointService;
import kr.hhplus.be.server.point.infrastructure.lock.PointLockManager;
import kr.hhplus.be.server.point.presentation.dto.response.PointHistoryResponse;
import kr.hhplus.be.server.point.presentation.dto.response.PointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PointUseCaseImpl implements PointUseCase {

    private final PointLockManager pointLockManager;
    private final PointService pointService;
    private final PointRepository pointRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Override
    @Transactional
    public PointResponse chargePoint(Long userId, BigDecimal amount, PointChangedType changedType) {

        // 포인트 타입 검증
        changedType.validateForCharge();

        Point point;

        try {

            // 락 획득 시도
            pointLockManager.getLock(userId);

            // 포인트 충전
            point = pointService.charge(userId, amount);

            // 이력 저장
            pointHistoryRepository.save(PointHistory.from(point, amount, changedType));

        } catch (InterruptedException e) {

            throw new PointException(ErrorCode.INTERNAL_POINT_ERROR);
        } finally {

            pointLockManager.releaseLock(userId);
        }

        return PointMapper.toDto(point);
    }

    @Override
    @Transactional
    public PointResponse usePoint(Long userId, BigDecimal amount, PointChangedType changedType) {

        // 포인트 타입 검증
        changedType.validateForUse();

        Point point;

        try {

            // 락 획득 시도
            pointLockManager.getLock(userId);

            // 포인트 충전
            point = pointService.use(userId, amount);

            // 이력 저장
            pointHistoryRepository.save(PointHistory.from(point, amount, changedType));

        } catch (InterruptedException e) {

            throw new PointException(ErrorCode.INTERNAL_POINT_ERROR);
        } finally {

            pointLockManager.releaseLock(userId);
        }

        return PointMapper.toDto(point);
    }

    @Override
    public PointResponse getPoint(Long userId) {

        Point point = pointRepository.findByUserId(userId)
                .orElseThrow(() -> new PointException(ErrorCode.POINT_NOT_FOUND));

        return PointMapper.toDto(point);
    }

    @Override
    public List<PointHistoryResponse> getPointHistories(Long userId) {

        Point point = pointRepository.findByUserId(userId)
                .orElseThrow(() -> new PointException(ErrorCode.POINT_NOT_FOUND));

        List<PointHistory> pointHistories = pointHistoryRepository.findAllByPointId(point.getId());

        return pointHistories.stream().map(PointMapper::toDto).toList();
    }
}
