package kr.hhplus.be.server.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "공통 응답 객체")
public class ApiResult<T> {

    @Schema(name = "code", description = "응답코드", example = "20000000", required = true)
    private String code;

    @Schema(name = "desc", description = "응답 메시지", example = "요청 성공", required = true)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "응답 데이터")
    private T result;

    @Builder
    private ApiResult(String code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public static <T> ApiResult<T> ok() {

        StatusCode success = StatusCode.OK;

        return ApiResult.<T>builder()
                .code(success.getCode())
                .message(success.getMessage())
                .result(null)
                .build();
    }

    public static <T> ApiResult<T> ok(T data) {

        StatusCode success = StatusCode.OK;

        return ApiResult.<T>builder()
                .code(success.getCode())
                .message(success.getMessage())
                .result(data)
                .build();
    }

    public static <T> ApiResult<T> error(String code, String desc) {

        return ApiResult.<T>builder()
                .code(code)
                .message(desc)
                .result(null)
                .build();
    }
}