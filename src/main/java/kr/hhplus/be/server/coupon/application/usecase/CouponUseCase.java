package kr.hhplus.be.server.coupon.application.usecase;

import kr.hhplus.be.server.coupon.application.dto.request.IssueCouponCommand;
import kr.hhplus.be.server.coupon.application.dto.response.CouponResult;
import kr.hhplus.be.server.coupon.application.port.in.CouponPort;
import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;
import kr.hhplus.be.server.coupon.domain.exception.CouponErrorCode;
import kr.hhplus.be.server.coupon.domain.exception.CouponException;
import kr.hhplus.be.server.coupon.domain.repository.CouponQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponUseCase implements CouponPort {

    private final CouponQueryRepository couponQueryRepository;

    @Override
    @Transactional
    public CouponResult issueCoupon(IssueCouponCommand command) {

        try {

            UserCoupon coupon = couponQueryRepository.findByTemplateIdWithLock(command.templateId())
                .orElseThrow(() -> new CouponException(CouponErrorCode.COUPON_NOT_FOUND));
            // 1. 남은 쿠폰 있는지 조회 없으면 에러 -> 여기서 template rowLock?

            // 2. 있으면 락획득 시도해서 발급 시도 -> usercoupon 할당되지 않은 rowLock

            // 3. 해당 쿠폰에 사용자 할당

            // 4. template 남은 수량 감소

            // 5. 쿠폰 정보 응답
        }


        return null;
    }
}
