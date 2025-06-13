package kr.hhplus.be.server.coupon.domain.service;

import jakarta.persistence.LockTimeoutException;
import java.util.Optional;
import kr.hhplus.be.server.coupon.domain.CouponStatus;
import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;
import kr.hhplus.be.server.coupon.domain.exception.CouponErrorCode;
import kr.hhplus.be.server.coupon.domain.exception.CouponException;
import kr.hhplus.be.server.coupon.domain.repository.CouponQueryRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.PessimisticLockException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponQueryRepository couponQueryRepository;

    public UserCoupon issueCoupon(long userId, long templateId) {

        try {

            if (couponQueryRepository.findByTemplateIdAndUserId(templateId, userId).isPresent())
                throw new CouponException(CouponErrorCode.DUPLICATE_COUPON_ISSUE);

            // 미리 생성된 쿠폰 조회 rowLock 및 skip Locked를 사용
            UserCoupon coupon = couponQueryRepository.findByTemplateIdWithLock(templateId)
                .orElseThrow(() -> new CouponException(CouponErrorCode.COUPON_NOT_FOUND));

            if (coupon.isAssigned()) throw new CouponException(CouponErrorCode.COUPON_ALREADY_ASSIGNED);
            if (coupon.isExpired()) throw new CouponException(CouponErrorCode.COUPON_EXPIRED);

            coupon.issueCoupon(userId);

            return coupon;

        } catch (PessimisticLockException | LockTimeoutException e) {

            throw new CouponException(CouponErrorCode.LOCK_ACQUISITION_FAILED);
        }
    }

    public UserCoupon useCoupon(long couponId, long orderId, BigDecimal totalPrice) {

        try {

            UserCoupon coupon = couponQueryRepository.findByCouponIdWithLock(couponId)
                .orElseThrow(() -> new CouponException(CouponErrorCode.COUPON_NOT_FOUND));

            if (coupon.isNotAvailable()) throw new CouponException(CouponErrorCode.COUPON_NOT_AVAILABLE);
            if (coupon.isExpired()) throw new CouponException(CouponErrorCode.COUPON_EXPIRED);
            if (coupon.isBelowMinimumPrice(totalPrice)) throw new CouponException(CouponErrorCode.INVALID_COUPON_MINIMUM_PRICE);

            coupon.useCoupon(orderId);

            return coupon;

        } catch (PessimisticLockException | LockTimeoutException e) {

            throw new CouponException(CouponErrorCode.LOCK_ACQUISITION_FAILED);
        }
    }

    public boolean validateCoupon(long userId, long couponId, BigDecimal totalPrice) {

        Optional<UserCoupon> coupon = couponQueryRepository
            .findByCouponIdAndUserIdAndStatus(couponId, userId, CouponStatus.AVAILABLE);

        if(coupon.isEmpty()) return false;


    }
}
