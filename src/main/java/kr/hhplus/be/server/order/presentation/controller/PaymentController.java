package kr.hhplus.be.server.order.presentation.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import kr.hhplus.be.server.global.config.security.CustomUserDetails;
import kr.hhplus.be.server.global.dto.ApiResult;
import kr.hhplus.be.server.order.presentation.dto.request.CreatePaymentRequest;
import kr.hhplus.be.server.order.presentation.dto.response.CreatePaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    @PostMapping("")
    public ResponseEntity<ApiResult<CreatePaymentResponse>> ready(
        @NotBlank @RequestHeader("Idempotency-Key") String idempotencyKey,
        @AuthenticationPrincipal CustomUserDetails user,
        @Valid @RequestBody CreatePaymentRequest request
    ) {



        return ResponseEntity.ok(
            ApiResult.ok()
        );
    }
}
