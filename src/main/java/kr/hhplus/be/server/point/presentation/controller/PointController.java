package kr.hhplus.be.server.point.presentation.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kr.hhplus.be.server.global.dto.ApiResult;
import kr.hhplus.be.server.point.application.port.in.PointPort;
import kr.hhplus.be.server.point.presentation.dto.request.PointRequest;
import kr.hhplus.be.server.point.presentation.dto.response.PointHistoryResponse;
import kr.hhplus.be.server.point.presentation.dto.response.PointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/points")
public class PointController {

    private final PointPort pointPort;

    @GetMapping("{userId}")
    public ResponseEntity<ApiResult<PointResponse>> getPoint(
            @PathVariable @Min(1) long userId
    ) {

        PointResponse response = pointPort.getPoint(userId);

        return ResponseEntity.ok(
                ApiResult.ok(response)
        );
    }

    @GetMapping("{userId}/histories")
    public ResponseEntity<ApiResult<List<PointHistoryResponse>>> getPointHistories(
            @PathVariable @Min(1) long userId
    ) {

        List<PointHistoryResponse> response = pointPort.getPointHistories(userId);

        return ResponseEntity.ok(
                ApiResult.ok(response)
        );
    }

    @PatchMapping("{userId}/charge")
    public ResponseEntity<ApiResult<PointResponse>> chargePoint(
            @PathVariable @Min(1) long userId,
            @RequestBody @Valid PointRequest request
    ) {

        PointResponse response = pointPort.chargePoint(userId, request.amount(), request.changedType());

        return ResponseEntity.ok(
                ApiResult.ok(response)
        );
    }

    @PatchMapping("{userId}/use")
    public ResponseEntity<ApiResult<PointResponse>> usePoint(
            @PathVariable @Min(1) long userId,
            @RequestBody @Valid PointRequest request
    ) {

        PointResponse response = pointPort.usePoint(userId, request.amount(), request.changedType());

        return ResponseEntity.ok(
                ApiResult.ok(response)
        );
    }
}
