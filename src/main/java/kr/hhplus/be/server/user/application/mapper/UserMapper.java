package kr.hhplus.be.server.user.application.mapper;

import kr.hhplus.be.server.user.domain.entity.User;
import kr.hhplus.be.server.user.presentation.dto.request.RegisterUserRequestDto;

public class UserMapper {

    public static User toEntity(RegisterUserRequestDto request) {

        return User.builder()
                .userId(request.userId())
                .name(request.name())
                .type(request.type())
                .build();
    }
}
