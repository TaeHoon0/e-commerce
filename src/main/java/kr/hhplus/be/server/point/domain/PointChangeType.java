package kr.hhplus.be.server.point.domain;

import kr.hhplus.be.server.point.domain.exception.PointErrorCode;
import kr.hhplus.be.server.point.domain.exception.PointException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PointChangeType {

    CHARGE(true),
    USE(false),
    ROLLBACK(true),
    REFUND(true);

    private final boolean isIncrease;

    public boolean isIncreaseType() {
        return isIncrease;
    }

    public boolean isDecreaseType() {
        return !isIncrease;
    }

    // 각 도메인 메서드에 허용 가능한 타입인지 검사
    public void validateForCharge() {
        if (!this.isIncreaseType()) {
            throw new PointException(PointErrorCode.INVALID_CHANGED_TYPE);
        }
    }

    public void validateForUse() {
        if (!this.isDecreaseType()) {
            throw new PointException(PointErrorCode.INVALID_CHANGED_TYPE);
        }
    }
}
