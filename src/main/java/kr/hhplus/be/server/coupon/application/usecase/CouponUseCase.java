package kr.hhplus.be.server.coupon.application.usecase;

import kr.hhplus.be.server.coupon.application.dto.request.IssueCouponCommand;
import kr.hhplus.be.server.coupon.application.dto.request.UseCouponCommand;
import kr.hhplus.be.server.coupon.application.dto.response.CouponResult;
import kr.hhplus.be.server.coupon.application.mapper.CouponResultMapper;
import kr.hhplus.be.server.coupon.application.port.in.CouponPort;
import kr.hhplus.be.server.coupon.domain.entity.CouponTemplate;
import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;
import kr.hhplus.be.server.coupon.domain.entity.UserCouponHistory;
import kr.hhplus.be.server.coupon.domain.repository.CouponHistoryCommandRepository;
import kr.hhplus.be.server.coupon.domain.service.CouponService;
import kr.hhplus.be.server.coupon.domain.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponUseCase implements CouponPort {

    private final CouponService couponService;
    private final TemplateService templateService;
    private final CouponHistoryCommandRepository couponHistoryCommandRepository;

    /**
     * <h3>쿠폰 발급</h3> <br>
     * 1. 템플릿 쿠폰 남은 수량 감소 <br>
     * 2. 미리 생성된 쿠폰을 조회하여 할당되지 않은 쿠폰 할당 및 사용 가능 상태로 변경
     */
    @Override
    @Transactional
    public CouponResult issueCoupon(IssueCouponCommand command) {

        CouponTemplate template = templateService.get(command.templateId());

        templateService.decreaseRemainCount(template);

        UserCoupon coupon = couponService.issueCoupon(command.userId(), template.getId());

        couponHistoryCommandRepository.save(UserCouponHistory.createIssueHistory(coupon.getUserId(), coupon));

        return CouponResultMapper.toResult(coupon);
    }

    @Override
    @Transactional
    public CouponResult useCoupon(UseCouponCommand command) {

        UserCoupon coupon = couponService.useCoupon(command.couponId(), command.orderId(), command.totalPrice());

        couponHistoryCommandRepository.save(UserCouponHistory.createUseHistory(coupon.getUserId(), coupon.getOrderId(), coupon));

        return CouponResultMapper.toResult(coupon);
    }
}
