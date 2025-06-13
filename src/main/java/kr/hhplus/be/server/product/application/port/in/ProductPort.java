package kr.hhplus.be.server.product.application.port.in;

import kr.hhplus.be.server.product.application.dto.request.AddProductOptionCommand;
import kr.hhplus.be.server.product.application.dto.request.DecreaseStockCommand;
import kr.hhplus.be.server.product.application.dto.request.RegisterProductCommand;
import kr.hhplus.be.server.product.application.dto.reseponse.ProductResult;
import kr.hhplus.be.server.product.domain.ProductStatus;
import kr.hhplus.be.server.product.domain.entity.ProductOption;
import kr.hhplus.be.server.product.presentation.dto.response.ProductOptionResponse;
import kr.hhplus.be.server.product.presentation.dto.response.ProductResponse;

import java.util.List;

public interface ProductPort {

    /**
     * 상품 조회
     */
    ProductResponse getProduct(Long productId);

    /**
     * 상품 등록
     */
    ProductResult registerProduct(RegisterProductCommand command);

    /**
     * 상품 수정
     */
    void updateProduct(Long productId, String name, String description, ProductStatus status);

    /**
     * 상품 옵션 추가
     */
    ProductResult addOptions(AddProductOptionCommand command);

    /**
     * 상품 재고 감소
     */
    void decreaseStock(DecreaseStockCommand command);
}
