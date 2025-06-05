package kr.hhplus.be.server.user.application.mapper;

import kr.hhplus.be.server.user.application.dto.response.UserResult;
import kr.hhplus.be.server.user.domain.entity.User;

public class UserResultMapper {

    public static UserResult toDto(User user) {

        return new UserResult(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getType()
        );
    }
}
