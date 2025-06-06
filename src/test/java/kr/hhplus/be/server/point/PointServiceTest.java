package kr.hhplus.be.server.point;

import kr.hhplus.be.server.point.domain.PointPolicy;
import kr.hhplus.be.server.point.domain.entity.Point;
import kr.hhplus.be.server.point.domain.exception.PointErrorCode;
import kr.hhplus.be.server.point.domain.exception.PointException;
import kr.hhplus.be.server.point.domain.service.PointService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PointServiceTest {

    @Mock
    private PointPolicy pointPolicy;

    @InjectMocks
    private PointService pointService;

    @Test
    void 신규_포인트_충전_성공() {

        // Given
        Long userId = 1L;
        BigDecimal chargeAmount = BigDecimal.valueOf(1_000);
        Point newPoint = Point.create(userId);

        doNothing().when(pointPolicy).validateMaxPoint(BigDecimal.ZERO, chargeAmount);

        // When
        pointService.charge(newPoint, chargeAmount);

        // Then
        verify(pointPolicy, times(1)).validateMaxPoint(BigDecimal.ZERO, chargeAmount);
        assertEquals(chargeAmount, newPoint.getAmount());
    }

    @Test
    void 포인트_충전_실패_보유량_초과() {
        // Given
        Long userId = 2L;
        BigDecimal existingAmount = BigDecimal.valueOf(5_000);
        BigDecimal chargeAmount = BigDecimal.valueOf(10_000_000);

        Point existingPoint = Point.create(userId);
        existingPoint.charge(existingAmount);

        doThrow(new PointException(PointErrorCode.EXCEEDS_MAX_POINT))
            .when(pointPolicy).validateMaxPoint(existingAmount, chargeAmount);

        // When & Then
        PointException ex = assertThrows(PointException.class, () ->
            pointService.charge(existingPoint, chargeAmount)
        );

        // 예외 코드가 ABOVE_MAX_POINT인지 검증
        assertEquals(PointErrorCode.EXCEEDS_MAX_POINT, ex.getPointErrorCode());

        // Point의 amount가 변화하지 않았는지(= 5000) 검증
        assertEquals(existingAmount, existingPoint.getAmount());
    }

    @Test
    void 포인트_사용_성공() {
        // Given
        Long userId = 3L;
        BigDecimal existingAmount = BigDecimal.valueOf(2_000);
        BigDecimal useAmount = BigDecimal.valueOf(500);

        Point existingPoint = Point.create(userId);
        existingPoint.charge(existingAmount);

        doNothing().when(pointPolicy).validateMinPoint(existingAmount, useAmount);

        // When
        pointService.use(existingPoint, useAmount);

        // Then
        verify(pointPolicy, times(1)).validateMinPoint(existingAmount, useAmount);
        assertEquals(existingAmount.subtract(useAmount), existingPoint.getAmount());
    }

    @Test
    void 포인트_사용_실패_잔고_부족() {

        // Given
        Long userId = 4L;
        BigDecimal existingAmount = BigDecimal.valueOf(300);
        BigDecimal useAmount = BigDecimal.valueOf(1_000);

        Point existingPoint = Point.create(userId);
        existingPoint.charge(existingAmount);

        doThrow(new PointException(PointErrorCode.BELOW_MIN_POINT))
            .when(pointPolicy).validateMinPoint(existingAmount, useAmount);

        // When & Then
        PointException ex = assertThrows(PointException.class, () ->
            pointService.use(existingPoint, useAmount)
        );

        assertEquals(PointErrorCode.BELOW_MIN_POINT, ex.getPointErrorCode());
        assertEquals(existingAmount, existingPoint.getAmount());
    }
}
