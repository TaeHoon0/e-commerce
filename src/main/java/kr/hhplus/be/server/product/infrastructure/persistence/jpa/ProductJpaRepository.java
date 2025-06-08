package kr.hhplus.be.server.product.infrastructure.persistence.jpa;

import java.util.Optional;
import kr.hhplus.be.server.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    public Optional<Product> findByName(String name);
}
