package kr.hhplus.be.server.order.application.port.out;

import java.math.BigDecimal;
import kr.hhplus.be.server.order.application.dto.command.CouponCommand;

public interface OrderCouponPort {

    /**
     * 쿠폰 검증
     */
    boolean validateCoupon(Long userId, CouponCommand command, BigDecimal totalPrice);

    /**
     * 쿠폰 할인 금액 계산
     */
    BigDecimal calculateDiscountAmount(Long userId, Long couponId, BigDecimal totalPrice);
}
