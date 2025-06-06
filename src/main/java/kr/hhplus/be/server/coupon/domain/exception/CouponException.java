package kr.hhplus.be.server.coupon.domain.exception;

import lombok.Getter;

@Getter
public class CouponException extends RuntimeException {

    public CouponErrorCode errorCode;

    public CouponException(CouponErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
