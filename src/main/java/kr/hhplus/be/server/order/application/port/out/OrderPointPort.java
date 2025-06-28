package kr.hhplus.be.server.order.application.port.out;

import java.math.BigDecimal;

public interface OrderPointPort {

    /**
     * 포인트 검증
     */
    boolean validatePoint(long userId, BigDecimal pointAmount);
}
