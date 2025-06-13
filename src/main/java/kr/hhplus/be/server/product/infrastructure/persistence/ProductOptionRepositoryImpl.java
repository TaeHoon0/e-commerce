package kr.hhplus.be.server.product.infrastructure.persistence;

import kr.hhplus.be.server.product.domain.entity.ProductOption;
import kr.hhplus.be.server.product.domain.repository.ProductOptionQueryRepository;

import java.util.List;

public class ProductOptionRepositoryImpl implements ProductOptionQueryRepository {

    @Override
    public List<ProductOption> findByOptionIdsWithLock(List<Long> optionIds) {

        return List.of();
    }

}
