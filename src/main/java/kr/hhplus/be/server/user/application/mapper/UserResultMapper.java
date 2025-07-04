package kr.hhplus.be.server.user.application.mapper;

import kr.hhplus.be.server.user.application.dto.result.UserResult;
import kr.hhplus.be.server.user.domain.entity.User;

public class UserResultMapper {

    public static UserResult toResult(User user) {

        return new UserResult(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getType()
        );
    }
}
