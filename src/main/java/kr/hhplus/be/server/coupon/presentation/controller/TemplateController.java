package kr.hhplus.be.server.coupon.presentation.controller;

import jakarta.validation.Valid;
import kr.hhplus.be.server.coupon.application.dto.response.TemplateResult;
import kr.hhplus.be.server.coupon.application.port.in.TemplatePort;
import kr.hhplus.be.server.coupon.presentation.dto.request.TemplateRequest;
import kr.hhplus.be.server.coupon.presentation.dto.reseponse.CreateTemplateResponse;
import kr.hhplus.be.server.coupon.presentation.mapper.TemplateRequestMapper;
import kr.hhplus.be.server.coupon.presentation.mapper.TemplateResponseMapper;
import kr.hhplus.be.server.global.dto.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequestMapping("/api/coupons")
@RestController
@RequiredArgsConstructor
public class TemplateController {

    private final TemplatePort port;

    /**
     * 쿠폰 템플릿 생성
     */
    @PostMapping("/templates")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResult<CreateTemplateResponse>> createCouponTemplate(
        @RequestBody @Valid TemplateRequest request
    ) {

        TemplateResult result = port.createTemplate(TemplateRequestMapper.toCreateCommand(request));

        return ResponseEntity.ok(
            ApiResult.ok(TemplateResponseMapper.toResponse(result))
        );
    }
}
