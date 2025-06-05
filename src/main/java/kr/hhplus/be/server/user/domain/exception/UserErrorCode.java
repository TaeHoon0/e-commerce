package kr.hhplus.be.server.user.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "4040001", "해당 사용자를 찾을 수 없습니다."),
    DUPLICATE_USER_EMAIL(HttpStatus.CONFLICT, "4090001", "이미 존재하는 사용자 이메일입니다."),
    INVALID_USER_TYPE(HttpStatus.BAD_REQUEST, "4000001", "올바르지 않은 사용자 타입입니다."),
    NOT_MATCHED_PASSWORD(HttpStatus.BAD_REQUEST, "4000002", "비밀번호가 맞지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
