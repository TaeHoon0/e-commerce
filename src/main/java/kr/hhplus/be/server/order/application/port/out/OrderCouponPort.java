package kr.hhplus.be.server.order.application.port.out;

import java.math.BigDecimal;

public interface OrderCouponPort {

    /**
     * 쿠폰 검증
     */
    boolean validateCoupon(Long userId, Long couponId, BigDecimal totalPrice);

    /**
     * 쿠폰 할인 금액 계산
     */
    BigDecimal calculateDiscount(Long userId, Long couponId, BigDecimal totalPrice);
}
