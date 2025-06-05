package kr.hhplus.be.server.product.application.port.in;

import kr.hhplus.be.server.product.domain.ProductStatus;
import kr.hhplus.be.server.product.domain.entity.Product;
import kr.hhplus.be.server.product.domain.entity.ProductOption;
import kr.hhplus.be.server.product.presentation.dto.request.ProductOptionRequest;
import kr.hhplus.be.server.product.presentation.dto.response.ProductOptionResponse;
import kr.hhplus.be.server.product.presentation.dto.response.ProductResponse;

import java.util.List;

public interface ProductUseCase {

    /**
     * 상품 조회
     */
    ProductResponse getProduct(Long productId);

    /**
     * 상품 등록
     */
    ProductResponse registerProduct(Product product);

    /**
     * 상품 수정
     */
    void updateProduct(Long productId, String name, String description, ProductStatus status);

    /**
     * 상품 옵션 조회
     */
    List<ProductOptionResponse> getOptions(Long productId);

    /**
     * 상품 옵션 추가
     */
    ProductResponse addOptions(Long productId, List<ProductOption> options);
}
