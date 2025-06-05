package kr.hhplus.be.server.coupon.application.usecase;

import kr.hhplus.be.server.coupon.application.dto.request.IssueCouponCommand;
import kr.hhplus.be.server.coupon.application.dto.response.CouponResult;
import kr.hhplus.be.server.coupon.application.port.in.CouponPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponUseCase implements CouponPort {


    @Override
    public CouponResult issueCoupon(IssueCouponCommand command) {

        //TODO select for update, skip locked 사용
        return null;
    }
}
