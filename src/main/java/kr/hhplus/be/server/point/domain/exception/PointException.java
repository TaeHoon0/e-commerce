package kr.hhplus.be.server.point.domain.exception;

import lombok.Getter;

@Getter
public class PointException extends RuntimeException {

    private final PointErrorCode pointErrorCode;

    public PointException(PointErrorCode pointErrorCode) {
        super(pointErrorCode.getMessage());
        this.pointErrorCode = pointErrorCode;
    }
}
