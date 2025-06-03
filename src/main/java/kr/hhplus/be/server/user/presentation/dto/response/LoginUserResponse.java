package kr.hhplus.be.server.user.presentation.dto.response;

public record LoginUserResponse(

    String accessToken,

    String refreshToken
) {
}
