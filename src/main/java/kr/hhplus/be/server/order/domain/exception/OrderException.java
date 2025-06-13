package kr.hhplus.be.server.order.domain.exception;

import lombok.Getter;

@Getter
public class OrderException extends RuntimeException {

    public OrderErrorCode errorCode;

    public OrderException(OrderErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
