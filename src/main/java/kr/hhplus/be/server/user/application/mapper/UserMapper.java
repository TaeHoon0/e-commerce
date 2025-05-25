package kr.hhplus.be.server.user.application.mapper;

import kr.hhplus.be.server.user.domain.entity.User;
import kr.hhplus.be.server.user.presentation.dto.request.RegisterUserRequestDto;
import kr.hhplus.be.server.user.presentation.dto.response.UserResponseDto;

public class UserMapper {

    public static User toEntity(RegisterUserRequestDto request) {

        return User.builder()
                .userId(request.userId())
                .name(request.name())
                .type(request.type())
                .build();
    }

    public static UserResponseDto toDto(User user) {

        return new UserResponseDto(
                user.getId(),
                user.getUserId(),
                user.getName(),
                user.getType()
        );
    }
}
