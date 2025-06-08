package kr.hhplus.be.server.global.exception;

import kr.hhplus.be.server.coupon.domain.exception.CouponException;
import kr.hhplus.be.server.global.dto.ApiResult;
import kr.hhplus.be.server.global.dto.StatusCode;
import kr.hhplus.be.server.point.domain.exception.PointException;
import kr.hhplus.be.server.product.domain.exception.ProductException;
import kr.hhplus.be.server.user.domain.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<ApiResult<Void>> handleUserException(UserException e) {

        return ResponseEntity
                .status(e.getUserErrorCode().getStatus())
                .body(ApiResult.error(e.getUserErrorCode().getCode(), e.getMessage()));
    }

    @ExceptionHandler(value = PointException.class)
    public ResponseEntity<ApiResult<Void>> handlePointException(PointException e) {

        return ResponseEntity
                .status(e.getPointErrorCode().getStatus())
                .body(ApiResult.error(e.getPointErrorCode().getCode(), e.getMessage()));
    }

    @ExceptionHandler(value = ProductException.class)
    public ResponseEntity<ApiResult<Void>> handleProductException(ProductException e) {

        return ResponseEntity
                .status(e.getProductErrorCode().getStatus())
                .body(ApiResult.error(e.getProductErrorCode().getCode(), e.getMessage()));
    }

    @ExceptionHandler(value = CouponException.class)
    public ResponseEntity<ApiResult<Void>> handleCouponException(CouponException e) {

        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(ApiResult.error(e.getErrorCode().getCode(), e.getMessage()));
    }

    @ExceptionHandler({ IllegalArgumentException.class, MethodArgumentNotValidException.class })
    public ResponseEntity<ApiResult<Void>> handleBadRequest(Exception e) {

        StatusCode sc = StatusCode.BAD_REQUEST;

        return ResponseEntity
                .status(sc.getHttpStatus())
                .body(ApiResult.error(sc.getCode(), sc.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult<Void>> handleGenericException(Exception e) {

        StatusCode sc = StatusCode.INTERNAL_SERVER_ERROR;

        return ResponseEntity
                .status(sc.getHttpStatus())
                .body(ApiResult.error(sc.getCode(), sc.getMessage()));
    }
}
