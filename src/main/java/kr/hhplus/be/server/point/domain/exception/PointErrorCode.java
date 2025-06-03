package kr.hhplus.be.server.point.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PointErrorCode {

    POINT_NOT_FOUND(HttpStatus.NOT_FOUND, "4041001", "포인트 정보를 찾을 수 없습니다."),
    EXCEEDS_MAX_POINT(HttpStatus.BAD_REQUEST, "4001002", "보유 포인트 한도를 초과합니다."),
    BELOW_MIN_POINT(HttpStatus.BAD_REQUEST, "4001003", "보유 포인트가 부족합니다."),
    INVALID_POINT_AMOUNT(HttpStatus.BAD_REQUEST, "4001004", "유효하지 않은 포인트 금액입니다."),
    INVALID_CHANGED_TYPE(HttpStatus.BAD_REQUEST, "4001005", "요청에 맞지 않는 포인트 변경 타입입니다."),
    DUPLICATE_POINT_HISTORY(HttpStatus.CONFLICT, "4091005", "중복된 포인트 이력입니다."),
    LOCK_ACQUISITION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "5001006", "락 획득에 실패했습니다."),
    INTERNAL_POINT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "5001999", "포인트 처리 중 알 수 없는 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
