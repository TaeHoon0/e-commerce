package kr.hhplus.be.server.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StatusCode {

    OK(HttpStatus.OK, "2000000", "성공"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "4010000", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "4030000", "권한이 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
