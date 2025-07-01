package kr.hhplus.be.server.order.presentation.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import kr.hhplus.be.server.global.config.security.CustomUserDetails;
import kr.hhplus.be.server.global.dto.ApiResult;
import kr.hhplus.be.server.order.application.dto.result.ApproveOrderResult;
import kr.hhplus.be.server.order.application.dto.result.CreateOrderResult;
import kr.hhplus.be.server.order.application.port.in.OrderPort;
import kr.hhplus.be.server.order.presentation.dto.request.ApproveOrderRequest;
import kr.hhplus.be.server.order.presentation.dto.request.CreateOrderRequest;
import kr.hhplus.be.server.order.presentation.dto.response.ApproveOrderResponse;
import kr.hhplus.be.server.order.presentation.dto.response.CreateOrderResponse;
import kr.hhplus.be.server.order.presentation.mapper.OrderRequestMapper;
import kr.hhplus.be.server.order.presentation.mapper.OrderResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderPort port;

    /**
     * 주문 생성
     * @param idempotencyKey        주문 멱등키
     */
    @PostMapping("")
    public ResponseEntity<ApiResult<CreateOrderResponse>> create(
        @NotBlank @RequestHeader("Idempotency-Key") String idempotencyKey,
        @AuthenticationPrincipal CustomUserDetails user,
        @Valid CreateOrderRequest request
    ) {

        CreateOrderResult result = port.createOrder(
            OrderRequestMapper.toCreateCommand(user.getId(), idempotencyKey, request));

        return ResponseEntity.ok(
            ApiResult.ok(OrderResponseMapper.toResponse(result))
        );
    }

    @PostMapping("/approve")
    public ResponseEntity<ApiResult<ApproveOrderResponse>> approve(
        @AuthenticationPrincipal CustomUserDetails user,
        @Valid ApproveOrderRequest request
    ) {

        ApproveOrderResult result = port.approveOrder(
            OrderRequestMapper.toApproveCommand(user.getId(), request));

        return ResponseEntity.ok(
            ApiResult.ok(OrderResponseMapper.toResponse(result))
        );
    }
}
