package kr.hhplus.be.server.order.infrastructure.adapter;

import java.math.BigDecimal;
import kr.hhplus.be.server.coupon.application.dto.command.CalculateCouponQuery;
import kr.hhplus.be.server.coupon.application.dto.command.UseCouponCommand;
import kr.hhplus.be.server.coupon.application.dto.command.ValidateCouponQuery;
import kr.hhplus.be.server.coupon.application.port.in.CouponPort;
import kr.hhplus.be.server.order.application.dto.command.CouponCommand;
import kr.hhplus.be.server.order.application.port.out.OrderCouponPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponAdapter implements OrderCouponPort {

    private final CouponPort couponPort;

    @Override
    public boolean validateCoupon(Long userId, CouponCommand command, BigDecimal totalPrice) {

        return couponPort.validateCoupon(
            new ValidateCouponQuery(userId, command.couponId(), command.discountAmount(), totalPrice));
    }

    @Override
    public BigDecimal calculateDiscountAmount(Long userId, Long couponId, BigDecimal totalPrice) {

        return couponPort.calculateDiscountAmount(new CalculateCouponQuery(userId, couponId, totalPrice));
    }

    @Override
    public void useCoupon(Long couponId, Long orderId, BigDecimal totalPrice) {

        couponPort.useCoupon(new UseCouponCommand(couponId, orderId, totalPrice));
    }
}
