package kr.hhplus.be.server.user.presentation.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kr.hhplus.be.server.common.dto.ApiResult;
import kr.hhplus.be.server.user.application.mapper.UserMapper;
import kr.hhplus.be.server.user.application.port.in.UserUseCase;
import kr.hhplus.be.server.user.presentation.dto.request.RegisterUserRequestDto;
import kr.hhplus.be.server.user.presentation.dto.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping("")
    public ResponseEntity<ApiResult<UserResponseDto>> registerUser(
            @RequestBody @Valid RegisterUserRequestDto request
    ) {

        UserResponseDto response = userUseCase.register(UserMapper.toEntity(request));

        return ResponseEntity.ok(
            ApiResult.ok(response)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<UserResponseDto>> getUser(
            @PathVariable(name = "id") @Min(1) Long id
    ) {

        UserResponseDto response = userUseCase.get(id);

        return ResponseEntity.ok(
            ApiResult.ok(response)
        );
    }
}
