package kr.hhplus.be.server.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StatusCode {

    OK(HttpStatus.OK, "2000000", "성공");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
