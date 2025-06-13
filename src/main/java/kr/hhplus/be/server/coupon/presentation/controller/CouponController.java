package kr.hhplus.be.server.coupon.presentation.controller;

import jakarta.validation.Valid;
import kr.hhplus.be.server.coupon.application.dto.result.CouponResult;
import kr.hhplus.be.server.coupon.application.port.in.CouponPort;
import kr.hhplus.be.server.coupon.presentation.dto.request.IssueCouponRequest;
import kr.hhplus.be.server.coupon.presentation.dto.reseponse.CouponResponse;
import kr.hhplus.be.server.coupon.presentation.mapper.CouponRequestMapper;
import kr.hhplus.be.server.coupon.presentation.mapper.CouponResponseMapper;
import kr.hhplus.be.server.global.config.security.CustomUserDetails;
import kr.hhplus.be.server.global.dto.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/coupons/users")
@RequiredArgsConstructor
public class CouponController {

    private final CouponPort port;

    /**
     * 쿠폰 발급
     */
    @PostMapping("/issue")
    public ResponseEntity<ApiResult<CouponResponse>> issueCoupon(
        @AuthenticationPrincipal CustomUserDetails user,
        @RequestBody @Valid IssueCouponRequest request
    ) {

        CouponResult result = port.issueCoupon(CouponRequestMapper.toIssueCommand(user.getId(), request));

        return ResponseEntity.ok(
            ApiResult.ok(CouponResponseMapper.toResponse(result))
        );
    }

    /**
     * 쿠폰 사용
     */
    @PostMapping("/use")
    public ResponseEntity<ApiResult<Void>> useCoupon() {

        return null;
    }

    /**
     * 쿠폰 목록 조회
     */
    @GetMapping("/list")
    public ResponseEntity<ApiResult<Void>> getCouponList() {

        return null;
    }

}
