package kr.hhplus.be.server.user.presentation.mapper;

import kr.hhplus.be.server.user.application.dto.command.LoginUserCommand;
import kr.hhplus.be.server.user.application.dto.command.RegisterUserCommand;
import kr.hhplus.be.server.user.presentation.dto.request.LoginUserRequest;
import kr.hhplus.be.server.user.presentation.dto.request.RegisterUserRequest;

public class UserRequestMapper {

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
}
