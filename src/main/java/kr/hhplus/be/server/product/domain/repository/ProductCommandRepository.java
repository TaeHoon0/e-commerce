package kr.hhplus.be.server.product.domain.repository;

import kr.hhplus.be.server.product.domain.entity.Product;

public interface ProductCommandRepository {

    Product save(Product product);

}
