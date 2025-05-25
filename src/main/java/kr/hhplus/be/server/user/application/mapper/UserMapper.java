package kr.hhplus.be.server.user.application.mapper;

import kr.hhplus.be.server.user.domain.entity.User;
import kr.hhplus.be.server.user.presentation.dto.request.RegisterUserRequest;
import kr.hhplus.be.server.user.presentation.dto.response.UserResponse;

public class UserMapper {

    public static User toEntity(RegisterUserRequest request) {

        return User.builder()
                .email(request.email())
                .name(request.name())
                .type(request.type())
                .build();
    }

    public static UserResponse toDto(User user) {

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getType()
        );
    }
}
