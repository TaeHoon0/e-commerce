package kr.hhplus.be.server.user.application.dto.command;

import kr.hhplus.be.server.user.domain.UserType;

public record RegisterUserCommand(

    String email,
    String password,
    String name,
    UserType type
){

}
