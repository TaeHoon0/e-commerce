package kr.hhplus.be.server.user.application.mapper;

import kr.hhplus.be.server.user.application.dto.command.RegisterUserCommand;
import kr.hhplus.be.server.user.domain.entity.User;

public class UserCommandMapper {

    public static User toEntity(RegisterUserCommand command) {

        return User.builder()
                .email(command.email())
                .name(command.name())
                .type(command.type())
                .build();
    }
}
