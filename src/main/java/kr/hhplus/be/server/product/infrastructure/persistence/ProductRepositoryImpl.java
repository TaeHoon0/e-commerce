package kr.hhplus.be.server.product.infrastructure.persistence;

import kr.hhplus.be.server.product.domain.entity.Product;
import kr.hhplus.be.server.product.domain.repository.ProductCommandRepository;
import kr.hhplus.be.server.product.domain.repository.ProductQueryRepository;
import kr.hhplus.be.server.product.infrastructure.persistence.jpa.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductQueryRepository, ProductCommandRepository {

    private final ProductJpaRepository jpaRepository;

    @Override
    public Product save(Product product) {
        return jpaRepository.save(product);
    }

    @Override
    public Optional<Product> findByProductId(Long productId) {
        return jpaRepository.findById(productId);
    }

    @Override
    public Optional<Product> findByProductName(String productName) {
        return jpaRepository.findByName(productName);
    }
}
