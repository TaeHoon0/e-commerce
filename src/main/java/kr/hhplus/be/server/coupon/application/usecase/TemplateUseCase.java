package kr.hhplus.be.server.coupon.application.usecase;

import kr.hhplus.be.server.coupon.application.dto.request.CreateTemplateCommand;
import kr.hhplus.be.server.coupon.application.dto.response.TemplateResult;
import kr.hhplus.be.server.coupon.application.mapper.TemplateResultMapper;
import kr.hhplus.be.server.coupon.application.port.in.TemplatePort;
import kr.hhplus.be.server.coupon.domain.entity.CouponTemplate;
import kr.hhplus.be.server.coupon.domain.entity.UserCoupon;
import kr.hhplus.be.server.coupon.domain.repository.CouponCommandRepository;
import kr.hhplus.be.server.coupon.domain.repository.TemplateCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TemplateUseCase implements TemplatePort {

    private final TemplateCommandRepository templateCommandRepository;
    private final CouponCommandRepository couponCommandRepository;

    @Override
    @Transactional
    public TemplateResult createTemplate(CreateTemplateCommand command) {

        CouponTemplate template = CouponTemplate.create(
            command.templateName(),
            command.totalCount(),
            command.discountAmount(),
            command.minimumPrice(),
            command.expireDate()
        );

        template = templateCommandRepository.save(template);

        UserCoupon userCoupon = UserCoupon.create(
            template.getName(),
            template.getDiscountAmount(),
            template.getMinimumPrice(),
            template.getExpireDate(),
            template
        );

        couponCommandRepository.bulkInsert(userCoupon , command.totalCount());

        return TemplateResultMapper.toResult(template);
    }
}
