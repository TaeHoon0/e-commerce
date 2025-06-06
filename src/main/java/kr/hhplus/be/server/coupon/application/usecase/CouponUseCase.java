package kr.hhplus.be.server.coupon.application.usecase;

import jakarta.persistence.LockTimeoutException;
import kr.hhplus.be.server.coupon.application.dto.request.IssueCouponCommand;
import kr.hhplus.be.server.coupon.application.dto.response.CouponResult;
import kr.hhplus.be.server.coupon.application.mapper.CouponResultMapper;
import kr.hhplus.be.server.coupon.application.port.in.CouponPort;
import kr.hhplus.be.server.coupon.domain.entity.CouponTemplate;
import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;
import kr.hhplus.be.server.coupon.domain.exception.CouponErrorCode;
import kr.hhplus.be.server.coupon.domain.exception.CouponException;
import kr.hhplus.be.server.coupon.domain.repository.CouponQueryRepository;
import kr.hhplus.be.server.coupon.domain.repository.TemplateCommandRepository;
import kr.hhplus.be.server.coupon.domain.repository.TemplateQueryRepository;
import kr.hhplus.be.server.coupon.domain.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.hibernate.PessimisticLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponUseCase implements CouponPort {

    private final CouponService couponService;
    private final CouponQueryRepository couponQueryRepository;
    private final TemplateCommandRepository templateCommandRepository;
    private final TemplateQueryRepository templateQueryRepository;

    @Override
    @Transactional
    public CouponResult issueCoupon(IssueCouponCommand command) {

        UserCoupon coupon;

        try {
            CouponTemplate template = templateQueryRepository.findByTemplateId(command.templateId())
                    .orElseThrow(() -> new CouponException(CouponErrorCode.COUPON_TEMPLATE_NOT_FOUND));

            if(couponQueryRepository.findByTemplateIdAndUserId(command.templateId(), command.userId()).isPresent())
                throw new CouponException(CouponErrorCode.DUPLICATE_COUPON_ISSUE);

            // 미리 생성된 쿠폰 조회 rowLock 및 skip Locked를 사용
            coupon = couponQueryRepository.findByTemplateIdWithLock(template.getId())
                .orElseThrow(() -> new CouponException(CouponErrorCode.COUPON_NOT_FOUND));

            // 쿠폰 발급
            couponService.issueCoupon(command.userId(), coupon);

            // 원자적 감소를 사용하여 template 남은 수량과 발급 수량 정합성 유지
            long affected = templateCommandRepository.decreaseRemainCount(template.getId());

            if(affected == 0)
                throw new CouponException(CouponErrorCode.COUPON_OUT_OF_STOCK);

        } catch (PessimisticLockException | LockTimeoutException e) {

            throw new CouponException(CouponErrorCode.LOCK_ACQUISITION_FAILED);
        }

        return CouponResultMapper.toResult(coupon);
    }
}
