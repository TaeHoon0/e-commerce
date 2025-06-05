package kr.hhplus.be.server.point;

import kr.hhplus.be.server.point.domain.PointPolicy;
import kr.hhplus.be.server.point.domain.entity.Point;
import kr.hhplus.be.server.point.domain.exception.PointErrorCode;
import kr.hhplus.be.server.point.domain.exception.PointException;
import kr.hhplus.be.server.point.domain.repository.PointCommandRepository;
import kr.hhplus.be.server.point.domain.repository.PointQueryRepository;
import kr.hhplus.be.server.point.domain.service.PointService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PointServiceTest {

    @Mock
    private PointPolicy pointPolicy;

    @Mock
    private PointQueryRepository pointQueryRepository;

    @Mock
    private PointCommandRepository pointCommandRepository;

    @InjectMocks
    private PointService pointService;

    @Test
    void 신규_가입자_포인트_충전_성공() {

        //given
        Long userId = 1L;
        BigDecimal amount = BigDecimal.valueOf(1000);

        when(pointQueryRepository.findByUserIdWithLock(userId)).thenReturn(Optional.empty());

        Point newPoint = Point.create(userId);
        when(pointCommandRepository.save(any(Point.class))).thenReturn(newPoint);

        doNothing().when(pointPolicy).validateMaxPoint(BigDecimal.ZERO, amount);

        //when
        Point result = pointService.charge(userId, amount);

        //then
        verify(pointCommandRepository, times(1)).save(any(Point.class));
        assertEquals(amount, result.getAmount());
    }

    @Test
    void 포인트_갖고있는_사용자_포인트_충전_성공() {

        //given
        Long userId = 2L;
        BigDecimal existingAmount = BigDecimal.valueOf(5000);
        BigDecimal chargeAmount = BigDecimal.valueOf(2500);

        Point existingPoint = Point.create(userId);
        existingPoint.charge(existingAmount);

        when(pointQueryRepository.findByUserIdWithLock(userId)).thenReturn(Optional.of(existingPoint));
        doNothing().when(pointPolicy).validateMaxPoint(existingAmount, chargeAmount);

        //when
        Point result = pointService.charge(userId, chargeAmount);

        //then
        verify(pointCommandRepository, never()).save(any());
        assertEquals(existingAmount.add(chargeAmount), result.getAmount());
    }

    @Test
    void 포인트_사용_잔액_부족() {

        //given
        Long userId = 3L;
        BigDecimal existingAmount = BigDecimal.valueOf(1000);
        BigDecimal useAmount = BigDecimal.valueOf(2000);

        Point existingPoint = Point.create(userId);
        existingPoint.charge(existingAmount);

        when(pointQueryRepository.findByUserIdWithLock(userId)).thenReturn(Optional.of(existingPoint));

        doThrow(new PointException(PointErrorCode.BELOW_MIN_POINT))
                .when(pointPolicy).validateMinPoint(existingAmount, useAmount);

        //when, then
        PointException ex = assertThrows(PointException.class, () -> pointService.use(userId, useAmount));
        assertEquals(PointErrorCode.BELOW_MIN_POINT, ex.getPointErrorCode());
    }

    @Test
    void 포인트_사용_성공() {

        //given
        Long userId = 4L;
        BigDecimal existingAmount = BigDecimal.valueOf(100);
        BigDecimal useAmount = BigDecimal.valueOf(40);

        Point existingPoint = Point.create(userId);
        existingPoint.charge(existingAmount);

        when(pointQueryRepository.findByUserIdWithLock(userId)).thenReturn(Optional.of(existingPoint));
        doNothing().when(pointPolicy).validateMinPoint(existingAmount, useAmount);

        //when
        Point result = pointService.use(userId, useAmount);

        //then
        assertEquals(existingAmount.subtract(useAmount), result.getAmount());
    }
}
