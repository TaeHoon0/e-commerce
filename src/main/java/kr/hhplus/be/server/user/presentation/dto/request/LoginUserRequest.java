package kr.hhplus.be.server.user.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginUserRequest (
    @NotBlank @Size(min = 4, max = 30)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    String email,

    @NotBlank @Size(min = 4, max = 20)
    String password
){

}
