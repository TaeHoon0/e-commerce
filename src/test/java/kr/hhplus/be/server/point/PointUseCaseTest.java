package kr.hhplus.be.server.point;

import kr.hhplus.be.server.point.application.dto.request.ChargePointCommand;
import kr.hhplus.be.server.point.application.dto.request.UsePointCommand;
import kr.hhplus.be.server.point.application.dto.response.PointHistoryResult;
import kr.hhplus.be.server.point.application.usecase.in.PointUseCase;
import kr.hhplus.be.server.point.domain.PointChangeType;
import kr.hhplus.be.server.point.domain.entity.Point;
import kr.hhplus.be.server.point.domain.entity.PointHistory;
import kr.hhplus.be.server.point.domain.exception.PointErrorCode;
import kr.hhplus.be.server.point.domain.exception.PointException;
import kr.hhplus.be.server.point.domain.repository.PointHistoryCommandRepository;
import kr.hhplus.be.server.point.domain.repository.PointQueryRepository;
import kr.hhplus.be.server.point.domain.service.PointService;
import org.hibernate.PessimisticLockException;
import org.hibernate.exception.LockTimeoutException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PointUseCaseTest {

    @Mock private PointService pointService;
    @Mock private PointQueryRepository pointQueryRepository;
    @Mock private PointHistoryCommandRepository pointHistoryCommandRepository;
    @InjectMocks private PointUseCase pointUseCase;

    @Test
    void 포인트_충전_성공_및_이력_저장() {
        // given
        Long userId = 1L;
        BigDecimal amount = BigDecimal.valueOf(1000);
        ChargePointCommand command = mock(ChargePointCommand.class);
        when(command.userId()).thenReturn(userId);
        when(command.amount()).thenReturn(amount);
        when(command.type()).thenReturn(PointChangeType.CHARGE);

        Point dummyPoint = Point.create(userId);
        dummyPoint.charge(amount);
        when(pointService.charge(userId, amount, PointChangeType.CHARGE))
                .thenReturn(dummyPoint);

        // when
        var result = pointUseCase.chargePoint(command);

        // then
        verify(pointService).charge(userId, amount, PointChangeType.CHARGE);
        verify(pointHistoryCommandRepository).save(any(PointHistory.class));
        assertNotNull(result);
        assertEquals(userId, result.userId());
        assertEquals(amount, result.amount());
    }

    @Test
    void 포인트_충전_락_획득_실패() {
        // given
        Long userId = 2L;
        BigDecimal amount = BigDecimal.valueOf(500);
        ChargePointCommand command = mock(ChargePointCommand.class);
        when(command.userId()).thenReturn(userId);
        when(command.amount()).thenReturn(amount);
        when(command.type()).thenReturn(PointChangeType.CHARGE);

        when(pointService.charge(userId, amount, PointChangeType.CHARGE))
                .thenThrow(new PessimisticLockException(null, null, null));

        // when & then
        PointException ex = assertThrows(PointException.class,
                () -> pointUseCase.chargePoint(command));
        assertEquals(PointErrorCode.LOCK_ACQUISITION_FAILED, ex.getPointErrorCode());
        verify(pointHistoryCommandRepository, never()).save(any());
    }

    @Test
    void 포인트_사용_성공_및_이력_저장() {
        // given
        Long userId = 3L;
        BigDecimal initial = BigDecimal.valueOf(500);
        BigDecimal useAmt  = BigDecimal.valueOf(200);

        UsePointCommand command = mock(UsePointCommand.class);
        when(command.userId()).thenReturn(userId);
        when(command.amount()).thenReturn(useAmt);
        when(command.type()).thenReturn(PointChangeType.USE);

        Point dummyPoint = Point.create(userId);
        dummyPoint.charge(initial);
        dummyPoint.use(useAmt);
        when(pointService.use(userId, useAmt, PointChangeType.USE))
                .thenReturn(dummyPoint);

        // when
        var result = pointUseCase.usePoint(command);

        // then
        verify(pointService).use(userId, useAmt, PointChangeType.USE);
        verify(pointHistoryCommandRepository).save(any(PointHistory.class));
        assertNotNull(result);
        assertEquals(userId, result.userId());
        assertEquals(initial.subtract(useAmt), result.amount());
    }

    @Test
    void 포인트_사용_락_획득_실패() {
        // given
        Long userId = 4L;
        BigDecimal amount = BigDecimal.valueOf(100);
        UsePointCommand command = mock(UsePointCommand.class);
        when(command.userId()).thenReturn(userId);
        when(command.amount()).thenReturn(amount);
        when(command.type()).thenReturn(PointChangeType.USE);

        when(pointService.use(userId, amount, PointChangeType.USE))
                .thenThrow(new LockTimeoutException(null, null, null));

        // when & then
        PointException ex = assertThrows(PointException.class,
                () -> pointUseCase.usePoint(command));
        assertEquals(PointErrorCode.LOCK_ACQUISITION_FAILED, ex.getPointErrorCode());
        verify(pointHistoryCommandRepository, never()).save(any());
    }

    @Test
    void 포인트_조회_성공() {
        // given
        Long userId = 5L;
        Point point = Point.create(userId);
        point.charge(BigDecimal.valueOf(150));
        when(pointQueryRepository.findByUserId(userId))
                .thenReturn(Optional.of(point));

        // when
        var result = pointUseCase.getPoint(userId);

        // then
        assertNotNull(result);
        assertEquals(userId, result.userId());
        assertEquals(point.getAmount(), result.amount());
    }

    @Test
    void 포인트_조회_실패() {
        // given
        Long userId = 6L;
        when(pointQueryRepository.findByUserId(userId))
                .thenReturn(Optional.empty());

        // when & then
        PointException ex = assertThrows(PointException.class,
                () -> pointUseCase.getPoint(userId));
        assertEquals(PointErrorCode.POINT_NOT_FOUND, ex.getPointErrorCode());
    }

    @Test
    void 포인트_이력_조회_성공() {
        // given
        Long userId = 7L;
        Point point = Point.create(userId);
        point.charge(BigDecimal.valueOf(100));
        PointHistory hist = PointHistory.create(point, BigDecimal.valueOf(50), PointChangeType.CHARGE);
        point.getHistories().add(hist);
        when(pointQueryRepository.findByUserId(userId))
                .thenReturn(Optional.of(point));

        // when
        List<PointHistoryResult> results = pointUseCase.getPointHistories(userId);

        // then
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(hist.getChangedAmount(), results.get(0).changedAmount());
    }

    @Test
    void 포인트_이력_조회_실패() {
        // given
        Long userId = 8L;
        when(pointQueryRepository.findByUserId(userId))
                .thenReturn(Optional.empty());

        // when & then
        PointException ex = assertThrows(PointException.class,
                () -> pointUseCase.getPointHistories(userId));
        assertEquals(PointErrorCode.POINT_NOT_FOUND, ex.getPointErrorCode());
    }
}
