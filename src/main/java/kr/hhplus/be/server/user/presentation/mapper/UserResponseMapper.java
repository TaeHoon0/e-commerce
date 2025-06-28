package kr.hhplus.be.server.user.presentation.mapper;

import kr.hhplus.be.server.user.application.dto.result.LoginUserResult;
import kr.hhplus.be.server.user.application.dto.result.UserResult;
import kr.hhplus.be.server.user.presentation.dto.response.LoginUserResponse;
import kr.hhplus.be.server.user.presentation.dto.response.UserResponse;

public class UserResponseMapper {

    public static UserResponse toResponse(UserResult result) {

        return new UserResponse(
                result.id(),
                result.email(),
                result.name(),
                result.type()
        );
    }

    public static LoginUserResponse toResponse(LoginUserResult result) {

        return new LoginUserResponse(
                result.accessToken(),
                result.refreshToken()
        );
    }
}
