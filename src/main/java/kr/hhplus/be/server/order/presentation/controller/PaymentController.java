package kr.hhplus.be.server.order.presentation.controller;

import jakarta.validation.Valid;
import kr.hhplus.be.server.global.config.security.CustomUserDetails;
import kr.hhplus.be.server.global.dto.ApiResult;
import kr.hhplus.be.server.order.application.dto.result.ApprovePaymentResult;
import kr.hhplus.be.server.order.application.dto.result.CreatePaymentResult;
import kr.hhplus.be.server.order.application.port.in.PaymentPort;
import kr.hhplus.be.server.order.presentation.dto.request.ApprovePaymentRequest;
import kr.hhplus.be.server.order.presentation.dto.request.CreatePaymentRequest;
import kr.hhplus.be.server.order.presentation.dto.response.ApprovePaymentResponse;
import kr.hhplus.be.server.order.presentation.dto.response.CreatePaymentResponse;
import kr.hhplus.be.server.order.presentation.mapper.PaymentRequestMapper;
import kr.hhplus.be.server.order.presentation.mapper.PaymentResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentPort port;

    @PostMapping("")
    public ResponseEntity<ApiResult<CreatePaymentResponse>> ready(
        @AuthenticationPrincipal CustomUserDetails user,
        @Valid @RequestBody CreatePaymentRequest request
    ) {

        CreatePaymentResult result = port.ready(PaymentRequestMapper.toCreateCommand(user.getId(), request));

        return ResponseEntity.ok(
            ApiResult.ok(PaymentResponseMapper.toResponse(result))
        );
    }

    @PostMapping("/approve")
    public ResponseEntity<ApiResult<ApprovePaymentResponse>> approve(
        @AuthenticationPrincipal CustomUserDetails user,
        @Valid @RequestBody ApprovePaymentRequest request
    ) {

        ApprovePaymentResult result = port.approve(PaymentRequestMapper.toApproveCommand(user.getId(), request));

        return ResponseEntity.ok(
            ApiResult.ok(PaymentResponseMapper.toResponse(result))
        );
    }
}
