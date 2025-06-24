package kr.hhplus.be.server.order.presentation.controller;

import kr.hhplus.be.server.global.dto.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    @PostMapping("")
    public ResponseEntity<ApiResult<>> pending()
}
