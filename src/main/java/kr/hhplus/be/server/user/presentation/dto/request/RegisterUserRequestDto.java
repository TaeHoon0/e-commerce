package kr.hhplus.be.server.user.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kr.hhplus.be.server.user.domain.UserType;

public record RegisterUserRequestDto(
        @NotBlank @Size(min = 4, max = 30)
        String userId,

        @NotBlank @Size(min = 2, max = 30)
        String name,

        UserType type
){}
