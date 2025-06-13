package kr.hhplus.be.server.point.presentation.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kr.hhplus.be.server.global.config.security.CustomUserDetails;
import kr.hhplus.be.server.global.dto.ApiResult;
import kr.hhplus.be.server.point.application.dto.response.PointHistoryResult;
import kr.hhplus.be.server.point.application.dto.response.PointResult;
import kr.hhplus.be.server.point.application.port.in.PointPort;
import kr.hhplus.be.server.point.presentation.dto.request.PointRequest;
import kr.hhplus.be.server.point.presentation.dto.response.PointHistoryResponse;
import kr.hhplus.be.server.point.presentation.dto.response.PointResponse;
import kr.hhplus.be.server.point.presentation.mapper.PointHistoryResponseMapper;
import kr.hhplus.be.server.point.presentation.mapper.PointRequestMapper;
import kr.hhplus.be.server.point.presentation.mapper.PointResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/points")
public class PointController {

    private final PointPort pointPort;

    @GetMapping("")
    public ResponseEntity<ApiResult<PointResponse>> getPoint(
            @AuthenticationPrincipal CustomUserDetails user
        ) {

        PointResult result = pointPort.getPoint(user.getId());

        return ResponseEntity.ok(
            ApiResult.ok(PointResponseMapper.toResponse(result))
        );
    }

    @GetMapping("/histories")
    public ResponseEntity<ApiResult<List<PointHistoryResponse>>> getPointHistories(
            @AuthenticationPrincipal CustomUserDetails user
    ) {

        List<PointHistoryResult> result = pointPort.getPointHistories(user.getId());

        return ResponseEntity.ok(
            ApiResult.ok(result.stream().map(PointHistoryResponseMapper::toResponse).toList())
        );
    }

    @PatchMapping("/charge")
    public ResponseEntity<ApiResult<PointResponse>> chargePoint(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestBody @Valid PointRequest request
    ) {

        PointResult result = pointPort.chargePoint(PointRequestMapper.toChargeCommand(user.getId(), request));

        return ResponseEntity.ok(
                ApiResult.ok(PointResponseMapper.toResponse(result))
        );
    }

    @PatchMapping("/use")
    public ResponseEntity<ApiResult<PointResponse>> usePoint(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestBody @Valid PointRequest request
    ) {

        PointResult result = pointPort.usePoint(PointRequestMapper.toUseCommand(user.getId(), request));

        return ResponseEntity.ok(
                ApiResult.ok(PointResponseMapper.toResponse(result))
        );
    }
}
