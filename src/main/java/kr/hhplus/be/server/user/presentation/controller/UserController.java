package kr.hhplus.be.server.user.presentation.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kr.hhplus.be.server.global.dto.ApiResult;
import kr.hhplus.be.server.user.application.dto.response.LoginUserResult;
import kr.hhplus.be.server.user.application.dto.response.UserResult;
import kr.hhplus.be.server.user.application.port.in.UserPort;
import kr.hhplus.be.server.user.presentation.dto.request.LoginUserRequest;
import kr.hhplus.be.server.user.presentation.dto.request.RegisterUserRequest;
import kr.hhplus.be.server.user.presentation.dto.response.LoginUserResponse;
import kr.hhplus.be.server.user.presentation.dto.response.UserResponse;
import kr.hhplus.be.server.user.presentation.mapper.UserRequestMapper;
import kr.hhplus.be.server.user.presentation.mapper.UserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserPort userPort;

    @PostMapping("")
    public ResponseEntity<ApiResult<UserResponse>> registerUser(
            @RequestBody @Valid RegisterUserRequest request
    ) {

        UserResult result = userPort.register(UserRequestMapper.toRegisterCommand(request));

        return ResponseEntity.ok(
            ApiResult.ok(UserResponseMapper.toResponse(result))
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<UserResponse>> getUser(
            @PathVariable(name = "id") @Min(1) Long id
    ) {

        UserResult result = userPort.get(id);

        return ResponseEntity.ok(
            ApiResult.ok(UserResponseMapper.toResponse(result))
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResult<LoginUserResponse>> login(
        @RequestBody @Valid LoginUserRequest request
    ) {

        LoginUserResult result = userPort.login(UserRequestMapper.toLoginCommand(request));

        return ResponseEntity.ok(
            ApiResult.ok(UserResponseMapper.toResponse(result))
        );
    }
}
