package kr.hhplus.be.server.order.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OrderErrorCode {

    // 주문 조회/생성/취소 관련
    INVALID_ORDER_STATUS          (HttpStatus.BAD_REQUEST,          "4001001", "유효하지 않은 주문 상태입니다."),
    ORDER_ALREADY_COMPLETED       (HttpStatus.BAD_REQUEST,          "4001002", "이미 완료된 주문입니다."),
    INVALID_ORDER_REQUEST         (HttpStatus.BAD_REQUEST,          "4001003", "잘못된 주문 요청입니다."),
    ORDER_USER_NOT_MATCHED        (HttpStatus.BAD_REQUEST,          "4001004", "유효하지 않은 주문 사용자입니다."),
    ORDER_NOT_FOUND               (HttpStatus.NOT_FOUND,            "4041001", "주문을 찾을 수 없습니다."),
    ORDER_CREATION_FAILED         (HttpStatus.INTERNAL_SERVER_ERROR,"5001003", "주문 생성에 실패했습니다."),
    ORDER_CANCELLATION_FAILED     (HttpStatus.INTERNAL_SERVER_ERROR,"5001004", "주문 취소 처리에 실패했습니다."),

    // 결제 전용 상태 검증
    PAYMENT_NOT_READY             (HttpStatus.BAD_REQUEST,          "4001010", "결제 준비 상태가 아닙니다."),
    PAYMENT_ALREADY_PROCESSED     (HttpStatus.BAD_REQUEST,          "4001011", "이미 처리된 주문입니다."),
    PAYMENT_USER_NOT_MATCHED      (HttpStatus.BAD_REQUEST,          "4001012", "유효하지 않은 결제 사용자입니다."),
    PAYMENT_PRICE_NOT_MATCHED     (HttpStatus.BAD_REQUEST,          "4001013", "유효하지 않은 결제 금액입니다.."),
    PAYMENT_TID_NOT_MATCHED       (HttpStatus.BAD_REQUEST,          "4001014", "유효하지 않은 결제 번호입니다."),
    PAYMENT_NOT_FOUND             (HttpStatus.NOT_FOUND,            "4043001", "결제 정보를 찾을 수 없습니다."),
    PAYMENT_METHOD_NOT_SUPPORTED  (HttpStatus.BAD_REQUEST,          "4003002", "지원하지 않는 결제 수단입니다."),
    PAYMENT_INSUFFICIENT_FUNDS    (HttpStatus.BAD_REQUEST,          "4003003", "결제 금액이 부족합니다."),
    PAYMENT_TIMEOUT               (HttpStatus.REQUEST_TIMEOUT,      "4083004", "결제 요청이 시간 내에 처리되지 않았습니다."),
    PAYMENT_PROCESSING_FAILED     (HttpStatus.INTERNAL_SERVER_ERROR,"5003005", "결제 처리 중 오류가 발생했습니다."),


    // 환불/취소
    REFUND_NOT_ALLOWED            (HttpStatus.BAD_REQUEST,          "4003006", "환불이 허용되지 않습니다."),
    REFUND_PROCESSING_FAILED      (HttpStatus.INTERNAL_SERVER_ERROR,"5003007", "환불 처리에 실패했습니다."),
    INVALID_PAYMENT_STATUS        (HttpStatus.BAD_REQUEST,          "4003008", "잘못된 결제 상태입니다."),

    // 쿠폰
    INVALID_COUPON_REQUEST        (HttpStatus.BAD_REQUEST,          "4004001", "유효하지 않은 쿠폰입니다."),

    // 포인트
    INVALID_POINT_REQUEST         (HttpStatus.BAD_REQUEST,          "4005001", "유효하지 않은 포인트입니다."),

    // 상품
    INVALID_PRODUCT_REQUEST       (HttpStatus.BAD_REQUEST,          "4006001", "유효하지 않은 상품입니다.")
    ;


    private final HttpStatus status;
    private final String code;
    private final String message;
}
