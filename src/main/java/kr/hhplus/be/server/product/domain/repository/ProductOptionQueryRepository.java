package kr.hhplus.be.server.product.domain.repository;

import kr.hhplus.be.server.product.domain.entity.ProductOption;

import java.util.List;

public interface ProductOptionQueryRepository {

    List<ProductOption> findByOptionIdsWithLock(List<Long> optionIds);
}
