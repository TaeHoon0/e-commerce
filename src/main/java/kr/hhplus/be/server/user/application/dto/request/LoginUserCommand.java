package kr.hhplus.be.server.user.application.dto.request;

public record LoginUserCommand (

    String email,
    String password
){

}
