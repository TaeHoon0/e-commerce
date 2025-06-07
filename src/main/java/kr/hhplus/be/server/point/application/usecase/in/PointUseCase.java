package kr.hhplus.be.server.point.application.usecase.in;

import jakarta.persistence.LockTimeoutException;
import kr.hhplus.be.server.point.application.dto.request.ChargePointCommand;
import kr.hhplus.be.server.point.application.dto.request.UsePointCommand;
import kr.hhplus.be.server.point.application.dto.response.PointHistoryResult;
import kr.hhplus.be.server.point.application.dto.response.PointResult;
import kr.hhplus.be.server.point.application.mapper.PointHistoryResultMapper;
import kr.hhplus.be.server.point.application.mapper.PointResultMapper;
import kr.hhplus.be.server.point.application.port.in.PointPort;
import kr.hhplus.be.server.point.domain.entity.Point;
import kr.hhplus.be.server.point.domain.entity.PointHistory;
import kr.hhplus.be.server.point.domain.exception.PointErrorCode;
import kr.hhplus.be.server.point.domain.exception.PointException;
import kr.hhplus.be.server.point.domain.repository.PointCommandRepository;
import kr.hhplus.be.server.point.domain.repository.PointHistoryCommandRepository;
import kr.hhplus.be.server.point.domain.repository.PointQueryRepository;
import kr.hhplus.be.server.point.domain.service.PointService;
import lombok.RequiredArgsConstructor;
import org.hibernate.PessimisticLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointUseCase implements PointPort {

    private final PointService pointService;
    private final PointQueryRepository pointQueryRepository;
    private final PointHistoryCommandRepository pointHistoryCommandRepository;

    @Override
    @Transactional
    public PointResult chargePoint(ChargePointCommand command) {

            Point point = pointService.charge(command.userId(), command.amount(), command.type());

            pointHistoryCommandRepository.save(PointHistory.create(point, command.amount(), command.type()));

        return PointResultMapper.toResult(point);
    }

    @Override
    @Transactional
    public PointResult usePoint(UsePointCommand command) {

            Point point = pointService.use(command.userId(), command.amount(), command.type());

            pointHistoryCommandRepository.save(PointHistory.create(point, command.amount(), command.type()));

        return PointResultMapper.toResult(point);
    }

    @Override
    public PointResult getPoint(Long userId) {

        Point point = pointQueryRepository.findByUserId(userId)
                .orElseThrow(() -> new PointException(PointErrorCode.POINT_NOT_FOUND));

        return PointResultMapper.toResult(point);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PointHistoryResult> getPointHistories(Long userId) {

        Point point = pointQueryRepository.findByUserId(userId)
                .orElseThrow(() -> new PointException(PointErrorCode.POINT_NOT_FOUND));

        return point.getHistories().stream().map(PointHistoryResultMapper::toResult).toList();
    }
}
