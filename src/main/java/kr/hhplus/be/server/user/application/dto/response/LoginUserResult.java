package kr.hhplus.be.server.user.application.dto.response;

public record LoginUserResult (

        String accessToken,
        String refreshToken
){
}
