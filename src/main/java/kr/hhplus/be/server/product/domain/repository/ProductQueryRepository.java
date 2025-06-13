package kr.hhplus.be.server.product.domain.repository;

import kr.hhplus.be.server.product.domain.entity.Product;

import java.util.Optional;

public interface ProductQueryRepository {

    Optional<Product> findByProductId(Long productId);

    Optional<Product> findByProductName(String productName);
}
