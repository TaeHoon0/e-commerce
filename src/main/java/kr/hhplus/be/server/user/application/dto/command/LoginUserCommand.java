package kr.hhplus.be.server.user.application.dto.command;

public record LoginUserCommand (

    String email,
    String password
){

}
