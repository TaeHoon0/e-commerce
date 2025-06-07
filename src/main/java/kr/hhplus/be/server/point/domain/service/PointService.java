package kr.hhplus.be.server.point.domain.service;

import jakarta.persistence.LockTimeoutException;
import kr.hhplus.be.server.point.domain.PointChangeType;
import kr.hhplus.be.server.point.domain.PointPolicy;
import kr.hhplus.be.server.point.domain.entity.Point;

import java.math.BigDecimal;

import kr.hhplus.be.server.point.domain.exception.PointErrorCode;
import kr.hhplus.be.server.point.domain.exception.PointException;
import kr.hhplus.be.server.point.domain.repository.PointCommandRepository;
import kr.hhplus.be.server.point.domain.repository.PointQueryRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.PessimisticLockException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointPolicy pointPolicy;
    private final PointQueryRepository pointQueryRepository;
    private final PointCommandRepository pointCommandRepository;


    public Point charge(
            long userId , BigDecimal amount, PointChangeType type
    ) {

        try {

            Point point = pointQueryRepository.findByUserIdWithLock(userId)
                    .orElseGet(() -> pointCommandRepository.save(Point.create(userId)));

            type.validateForCharge();
            pointPolicy.validateMaxPoint(point.getAmount(), amount);

            point.charge(amount);

            return point;

        } catch (PessimisticLockException | LockTimeoutException e) {

            throw new PointException(PointErrorCode.LOCK_ACQUISITION_FAILED);
        }
    }

    public Point use(long userId, BigDecimal amount, PointChangeType type) {


        try {

            Point point = pointQueryRepository.findByUserIdWithLock(userId)
                    .orElseGet(() -> pointCommandRepository.save(Point.create(userId)));

            type.validateForUse();
            pointPolicy.validateMinPoint(point.getAmount(), amount);

            point.use(amount);

            return point;

        } catch (PessimisticLockException | LockTimeoutException e) {

            throw new PointException(PointErrorCode.LOCK_ACQUISITION_FAILED);
        }
    }
}
