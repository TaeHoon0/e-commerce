package kr.hhplus.be.server.user.application.mapper;

import kr.hhplus.be.server.user.application.dto.request.RegisterUserCommand;
import kr.hhplus.be.server.user.application.dto.response.UserResult;
import kr.hhplus.be.server.user.domain.entity.User;

public class UserApplicationMapper {

    public static User toEntity(RegisterUserCommand command) {

        return User.builder()
                .email(command.email())
                .name(command.name())
                .type(command.type())
                .build();
    }

    public static UserResult toDto(User user) {

        return new UserResult(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getType()
        );
    }
}
