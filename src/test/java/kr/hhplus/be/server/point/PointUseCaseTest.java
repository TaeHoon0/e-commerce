package kr.hhplus.be.server.point;

import kr.hhplus.be.server.point.application.dto.request.ChargePointCommand;
import kr.hhplus.be.server.point.application.dto.request.UsePointCommand;
import kr.hhplus.be.server.point.application.usecase.in.PointUseCase;
import kr.hhplus.be.server.point.domain.PointChangeType;
import kr.hhplus.be.server.point.domain.entity.Point;
import kr.hhplus.be.server.point.domain.exception.PointErrorCode;
import kr.hhplus.be.server.point.domain.exception.PointException;
import kr.hhplus.be.server.point.domain.repository.PointHistoryCommandRepository;
import kr.hhplus.be.server.point.domain.service.PointService;
import org.hibernate.PessimisticLockException;
import org.hibernate.exception.LockTimeoutException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PointUseCaseTest {

    @Mock private PointService pointService;
    @Mock private PointHistoryCommandRepository pointHistoryCommandRepository;
    @InjectMocks private PointUseCase pointUseCase;

    @Test
    void 포인트_충전_성공() {

        // Given
        Long userId = 1L;
        BigDecimal amount = BigDecimal.valueOf(7500);
        Point dummyPoint = Point.create(userId);
        dummyPoint.charge(amount);

        ChargePointCommand command = mock(ChargePointCommand.class);
        when(command.userId()).thenReturn(userId);
        when(command.amount()).thenReturn(amount);
        when(command.type()).thenReturn(PointChangeType.CHARGE);

        when(pointService.charge(userId, amount)).thenReturn(dummyPoint);

        // When
        var result = pointUseCase.chargePoint(command);

        // Then
        verify(pointHistoryCommandRepository, times(1)).save(any());
        assertNotNull(result);
        assertEquals(userId, result.userId());
        assertEquals(amount, result.amount());
    }

    @Test
    void 포인트_충전_락_획득_실패() {
        // Given
        Long userId = 11L;
        BigDecimal amount = BigDecimal.valueOf(30);

        ChargePointCommand command = mock(ChargePointCommand.class);
        when(command.userId()).thenReturn(userId);
        when(command.amount()).thenReturn(amount);
        when(command.type()).thenReturn(PointChangeType.CHARGE);

        when(pointService.charge(userId, amount))
                .thenThrow(new PessimisticLockException(null, null, null));

        // When, Then
        PointException ex = assertThrows(PointException.class, () ->
                pointUseCase.chargePoint(command)
        );

        assertEquals(PointErrorCode.LOCK_ACQUISITION_FAILED, ex.getPointErrorCode());
        verify(pointHistoryCommandRepository, never()).save(any());
    }

    @Test
    void 포인트_사용_성공() {

        // Given
        Long userId = 111L;
        BigDecimal amount = BigDecimal.valueOf(20);
        Point dummyPoint = Point.create(userId);
        dummyPoint.charge(BigDecimal.valueOf(100));
        dummyPoint.use(amount);

        UsePointCommand command = mock(UsePointCommand.class);
        when(command.userId()).thenReturn(userId);
        when(command.amount()).thenReturn(amount);
        when(command.type()).thenReturn(PointChangeType.USE);

        when(pointService.use(userId, amount)).thenReturn(dummyPoint);

        // When
        var result = pointUseCase.usePoint(command);

        // Then
        verify(pointHistoryCommandRepository, times(1)).save(any());
        assertNotNull(result);
        assertEquals(userId, result.userId());
        assertEquals(BigDecimal.valueOf(80), result.amount());
    }

    @Test
    void 포인트_사용_락_획득_타임아웃() {

        // Given
        Long userId = 13L;
        BigDecimal amount = BigDecimal.valueOf(10);

        UsePointCommand command = mock(UsePointCommand.class);
        when(command.userId()).thenReturn(userId);
        when(command.amount()).thenReturn(amount);
        when(command.type()).thenReturn(PointChangeType.USE);

        when(pointService.use(userId, amount))
                .thenThrow(new LockTimeoutException(null, null, null));

        // When, Then
        PointException ex = assertThrows(PointException.class, () ->
                pointUseCase.usePoint(command)
        );
        assertEquals(PointErrorCode.LOCK_ACQUISITION_FAILED, ex.getPointErrorCode());
        verify(pointHistoryCommandRepository, never()).save(any());
    }
}