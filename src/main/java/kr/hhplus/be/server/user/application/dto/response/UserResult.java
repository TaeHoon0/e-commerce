package kr.hhplus.be.server.user.application.dto.response;

import kr.hhplus.be.server.user.domain.UserType;

public record UserResult(

    Long id,
    String email,
    String name,
    UserType type

) {

}
