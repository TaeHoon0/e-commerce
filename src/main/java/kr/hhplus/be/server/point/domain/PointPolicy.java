package kr.hhplus.be.server.point.domain;

import kr.hhplus.be.server.point.domain.exception.ErrorCode;
import kr.hhplus.be.server.point.domain.exception.PointException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "point-policy")
public class PointPolicy {

    private final BigDecimal maxPoint;
    private final BigDecimal minPoint;

    public void validateMaxPoint(BigDecimal currentPoint, BigDecimal amount) {

        BigDecimal afterAmount = currentPoint.add(amount);

        if (afterAmount.compareTo(maxPoint) > 0) {
            throw new PointException(ErrorCode.EXCEEDS_MAX_POINT);
        }
    }

    public void validateMinPoint(BigDecimal currentPoint, BigDecimal amount) {

        BigDecimal afterAmount = currentPoint.subtract(amount);

        if (afterAmount.compareTo(minPoint) < 0) {
            throw new PointException(ErrorCode.BELOW_MIN_POINT);
        }
    }
}
