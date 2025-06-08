package kr.hhplus.be.server.product.domain.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProductErrorCode {

    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "4042001", "상품을 찾을 수 없습니다."),
    PRODUCT_NOT_ACTIVE(HttpStatus.BAD_REQUEST, "4002002", "판매중인 상품이 아닙니다."),
    INVALID_PRODUCT_DATA(HttpStatus.BAD_REQUEST, "4002003", "상품 정보가 유효하지 않습니다."),
    OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "4002101", "상품 재고가 부족합니다."),
    INVALID_STOCK_QUANTITY(HttpStatus.BAD_REQUEST, "4002102", "유효하지 않은 재고 수량입니다."),
    DUPLICATE_PRODUCT_NAME(HttpStatus.BAD_REQUEST, "4002004", "이미 존재하는 상품 이름입니다."),
    EXCEED_PRODUCT_OPTION(HttpStatus.BAD_REQUEST, "4002103", "상품 옵션 개수가 최대치 5개를 초과했습니다."),

    PRODUCT_LOCK_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "5002005", "상품 락 획득에 실패했습니다."),
    INTERNAL_PRODUCT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "5002999", "상품 처리 중 알 수 없는 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
