package kr.hhplus.be.server.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StatusCode {

    OK(HttpStatus.OK, "2000000", "성공"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "4010000", "인증이 필요합니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "4000000", "잘못된 요청입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "4030000", "권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "4040000", "리소스를 찾을 수 없습니다."),
    CONFLICT(HttpStatus.CONFLICT, "4090000", "요청이 충돌했습니다."),
    UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, "4220000", "유효성 검사에 실패했습니다."),
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "4290000", "요청이 너무 많습니다. 잠시 후 다시 시도해주세요."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "5000000", "서버 내부 오류가 발생했습니다.");
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
