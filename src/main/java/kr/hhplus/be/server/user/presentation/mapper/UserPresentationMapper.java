package kr.hhplus.be.server.user.presentation.mapper;

import kr.hhplus.be.server.user.application.dto.request.LoginUserCommand;
import kr.hhplus.be.server.user.application.dto.request.RegisterUserCommand;
import kr.hhplus.be.server.user.application.dto.response.UserResult;
import kr.hhplus.be.server.user.presentation.dto.request.LoginUserRequest;
import kr.hhplus.be.server.user.presentation.dto.request.RegisterUserRequest;
import kr.hhplus.be.server.user.presentation.dto.response.UserResponse;

public class UserPresentationMapper {

    public static RegisterUserCommand toRegisterCommand(RegisterUserRequest request) {

        return new RegisterUserCommand(
            request.email(),
            request.password(),
            request.name(),
            request.type()
        );
    }

    public static LoginUserCommand toLoginCommand(LoginUserRequest request) {

        return new LoginUserCommand(
            request.email(),
            request.password()
        );
    }

    public static UserResponse toResponse(UserResult result) {

        return new UserResponse(
            result.id(),
            result.email(),
            result.name(),
            result.type()
        );
    }
}
