package kr.hhplus.be.server.point.domain.service;

import kr.hhplus.be.server.point.domain.PointPolicy;
import kr.hhplus.be.server.point.domain.entity.Point;
import kr.hhplus.be.server.point.domain.exception.ErrorCode;
import kr.hhplus.be.server.point.domain.exception.PointException;
import kr.hhplus.be.server.point.domain.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointPolicy pointPolicy;
    private final PointRepository pointRepository;

    public Point charge(Long userId, BigDecimal amount) {

        Point point = pointRepository.findByUserId(userId)
                .orElseGet(() -> pointRepository.save(Point.createNew(userId)));

        // 보유한도 검증
        pointPolicy.validateMaxPoint(point.getAmount(), amount);

        point.charge(amount);

        return point;
    }

    public Point use(Long userId, BigDecimal amount) {

        Point point = pointRepository.findByUserId(userId)
                .orElseThrow(() -> new PointException(ErrorCode.POINT_NOT_FOUND));

        pointPolicy.validateMinPoint(point.getAmount(), amount);

        point.use(amount);

        return point;
    }
}
