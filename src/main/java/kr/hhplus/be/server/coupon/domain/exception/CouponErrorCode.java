package kr.hhplus.be.server.coupon.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CouponErrorCode {

    // 쿠폰 조회 관련
    COUPON_NOT_FOUND(HttpStatus.NOT_FOUND, "4042001", "쿠폰 정보를 찾을 수 없습니다."),
    COUPON_TEMPLATE_NOT_FOUND(HttpStatus.NOT_FOUND, "4042002", "쿠폰 템플릿을 찾을 수 없습니다."),

    // 쿠폰 할당/사용 관련
    COUPON_ALREADY_ASSIGNED(HttpStatus.BAD_REQUEST, "4002001", "이미 할당된 쿠폰입니다."),
    COUPON_NOT_AVAILABLE(HttpStatus.BAD_REQUEST, "4002002", "사용 가능한 쿠폰이 아닙니다."),
    COUPON_ALREADY_USED(HttpStatus.BAD_REQUEST, "4002003", "이미 사용된 쿠폰입니다."),
    COUPON_EXPIRED(HttpStatus.BAD_REQUEST, "4002004", "만료된 쿠폰입니다."),
    COUPON_ASSIGN_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "5002005", "쿠폰 할당에 실패했습니다."),
    COUPON_OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "4002013", "모두 할당되어 더 이상 쿠폰을 발급할 수 없습니다."),

    // 락 관련
    LOCK_ACQUISITION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "5002006", "쿠폰 락 획득에 실패했습니다."),
    LOCK_TIMEOUT(HttpStatus.INTERNAL_SERVER_ERROR, "5002007", "쿠폰 락 획득 시간이 초과되었습니다."),

    // 중복 발급/이력 관련
    DUPLICATE_COUPON_ISSUE(HttpStatus.CONFLICT, "4092008", "중복된 쿠폰 발급 요청입니다."),
    DUPLICATE_COUPON_HISTORY(HttpStatus.CONFLICT, "4092009", "중복된 쿠폰 이력입니다."),

    // 유효 기간, 금액 등 입력 값 검증
    INVALID_COUPON_AMOUNT(HttpStatus.BAD_REQUEST, "4002010", "유효하지 않은 쿠폰 할인 금액입니다."),
    INVALID_COUPON_MINIMUM_PRICE(HttpStatus.BAD_REQUEST, "4002011", "유효하지 않은 최소 사용 금액입니다."),
    INVALID_COUPON_EXPIRE_DATE(HttpStatus.BAD_REQUEST, "4002012", "유효하지 않은 쿠폰 만료 일자입니다."),

    // 내부 오류
    INTERNAL_COUPON_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "5002999", "쿠폰 처리 중 알 수 없는 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
