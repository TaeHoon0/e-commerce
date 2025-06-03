package kr.hhplus.be.server.user.presentation.dto.response;

public record UserLoginResponse (

    String accessToken,

    String refreshToken
) {
}
