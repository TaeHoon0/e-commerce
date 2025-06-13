package kr.hhplus.be.server.point.domain.service;

import kr.hhplus.be.server.point.domain.PointChangeType;
import kr.hhplus.be.server.point.domain.entity.Point;
import kr.hhplus.be.server.point.domain.exception.PointErrorCode;
import kr.hhplus.be.server.point.domain.exception.PointException;
import kr.hhplus.be.server.point.domain.repository.PointCommandRepository;
import kr.hhplus.be.server.point.domain.repository.PointQueryRepository;
import org.hibernate.PessimisticLockException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PointServiceTest {

    @Mock
    private PointPolicy pointPolicy;

    @Mock
    private PointQueryRepository queryRepo;

    @Mock
    private PointCommandRepository commandRepo;

    @InjectMocks
    private PointService pointService;

    @Test
    void 신규_포인트_충전_성공() {
        // given
        Long userId = 1L;
        BigDecimal amount = BigDecimal.valueOf(1000);
        Point newPoint = Point.create(userId);

        when(queryRepo.findByUserIdWithLock(userId))
                .thenReturn(Optional.empty());
        when(commandRepo.save(any(Point.class)))
                .thenReturn(newPoint);
        // policy 검증 통과
        doNothing().when(pointPolicy).validateMaxPoint(BigDecimal.ZERO, amount);

        // when
        Point result = pointService.charge(userId, amount, PointChangeType.CHARGE);

        // then
        verify(queryRepo).findByUserIdWithLock(userId);
        verify(commandRepo).save(any(Point.class));
        verify(pointPolicy).validateMaxPoint(BigDecimal.ZERO, amount);
        assertEquals(amount, result.getAmount());
    }

    @Test
    void 포인트_충전_성공() {
        // given
        Long userId = 2L;
        BigDecimal existing = BigDecimal.valueOf(500);
        BigDecimal amount   = BigDecimal.valueOf(200);
        Point existingPoint = Point.create(userId);
        existingPoint.charge(existing);

        when(queryRepo.findByUserIdWithLock(userId))
                .thenReturn(Optional.of(existingPoint));
        // 저장(save) 호출되지 않도록 stub
        // policy 검증 통과
        doNothing().when(pointPolicy).validateMaxPoint(existing, amount);

        // when
        Point result = pointService.charge(userId, amount, PointChangeType.CHARGE);

        // then
        verify(queryRepo).findByUserIdWithLock(userId);
        verify(commandRepo, never()).save(any());
        verify(pointPolicy).validateMaxPoint(existing, amount);
        assertEquals(existing.add(amount), result.getAmount());
    }

    @Test
    void 포인트_한도_초과() {
        // given
        Long userId = 3L;
        BigDecimal existing = BigDecimal.valueOf(1000);
        BigDecimal amount   = BigDecimal.valueOf(2_000_000);
        Point point = Point.create(userId);
        point.charge(existing);

        when(queryRepo.findByUserIdWithLock(userId))
                .thenReturn(Optional.of(point));
        doThrow(new PointException(PointErrorCode.EXCEEDS_MAX_POINT))
                .when(pointPolicy).validateMaxPoint(existing, amount);

        // when & then
        PointException ex = assertThrows(PointException.class, () ->
                pointService.charge(userId, amount, PointChangeType.CHARGE)
        );

        assertEquals(PointErrorCode.EXCEEDS_MAX_POINT, ex.getPointErrorCode());
        assertEquals(existing, point.getAmount());
    }

    @Test
    void 포인트_충전_락_획득_실패() {
        // given
        Long userId = 4L;
        BigDecimal amount = BigDecimal.valueOf(100);
        when(queryRepo.findByUserIdWithLock(userId))
                .thenThrow(new PessimisticLockException(null, null, null));

        // when & then
        PointException ex = assertThrows(PointException.class, () ->
                pointService.charge(userId, amount, PointChangeType.CHARGE)
        );
        assertEquals(PointErrorCode.LOCK_ACQUISITION_FAILED, ex.getPointErrorCode());
    }

    @Test
    void 포인트_사용_성공() {
        // given
        Long userId = 5L;
        BigDecimal existing = BigDecimal.valueOf(1000);
        BigDecimal useAmount = BigDecimal.valueOf(400);
        Point point = Point.create(userId);
        point.charge(existing);

        when(queryRepo.findByUserIdWithLock(userId))
                .thenReturn(Optional.of(point));
        doNothing().when(pointPolicy).validateMinPoint(existing, useAmount);

        // when
        Point result = pointService.use(userId, useAmount, PointChangeType.USE);

        // then
        verify(pointPolicy).validateMinPoint(existing, useAmount);
        assertEquals(existing.subtract(useAmount), result.getAmount());
    }

    @Test
    void 포인트_사용_잔고_부족() {
        // given
        Long userId = 6L;
        BigDecimal existing = BigDecimal.valueOf(300);
        BigDecimal useAmount = BigDecimal.valueOf(500);
        Point point = Point.create(userId);
        point.charge(existing);

        when(queryRepo.findByUserIdWithLock(userId))
                .thenReturn(Optional.of(point));
        doThrow(new PointException(PointErrorCode.BELOW_MIN_POINT))
                .when(pointPolicy).validateMinPoint(existing, useAmount);

        // when & then
        PointException ex = assertThrows(PointException.class, () ->
                pointService.use(userId, useAmount, PointChangeType.USE)
        );
        assertEquals(PointErrorCode.BELOW_MIN_POINT, ex.getPointErrorCode());
        // 사용 금액이 차감되지 않아야 함
        assertEquals(existing, point.getAmount());
    }

    @Test
    void 포인트_사용_락_획득_실패() {
        // given
        Long userId = 7L;
        BigDecimal useAmount = BigDecimal.valueOf(100);
        when(queryRepo.findByUserIdWithLock(userId))
                .thenThrow(new PessimisticLockException(null, null, null));

        // when & then
        PointException ex = assertThrows(PointException.class, () ->
                pointService.use(userId, useAmount, PointChangeType.USE)
        );
        assertEquals(PointErrorCode.LOCK_ACQUISITION_FAILED, ex.getPointErrorCode());
    }
}
