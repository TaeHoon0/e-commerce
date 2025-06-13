package kr.hhplus.be.server.user.application.dto.result;

public record LoginUserResult (

        String accessToken,
        String refreshToken
){
}
