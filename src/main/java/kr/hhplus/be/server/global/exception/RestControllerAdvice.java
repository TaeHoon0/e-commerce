package kr.hhplus.be.server.global.exception;

import kr.hhplus.be.server.global.dto.ApiResult;
import kr.hhplus.be.server.point.domain.exception.PointException;
import kr.hhplus.be.server.product.domain.exception.ProductException;
import kr.hhplus.be.server.user.domain.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<ApiResult<Void>> handleUserException(UserException e) {

        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(ApiResult.error(e.getErrorCode().getCode(), e.getMessage()));
    }

    @ExceptionHandler(value = PointException.class)
    public ResponseEntity<ApiResult<Void>> handlePointException(PointException e) {

        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(ApiResult.error(e.getErrorCode().getCode(), e.getMessage()));
    }

    @ExceptionHandler(value = ProductException.class)
    public ResponseEntity<ApiResult<Void>> handleProductException(ProductException e) {

        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(ApiResult.error(e.getErrorCode().getCode(), e.getMessage()));
    }
}
