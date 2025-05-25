package kr.hhplus.be.server.point.domain.exception;

import lombok.Getter;

@Getter
public class PointException extends RuntimeException {

    private final ErrorCode errorCode;

    public PointException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
