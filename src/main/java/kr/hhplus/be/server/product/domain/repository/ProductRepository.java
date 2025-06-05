package kr.hhplus.be.server.product.domain.repository;

import kr.hhplus.be.server.product.domain.entity.Product;

import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    Optional<Product> get(Long productId);
}
