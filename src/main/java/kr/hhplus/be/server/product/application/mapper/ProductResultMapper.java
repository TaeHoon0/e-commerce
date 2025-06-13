package kr.hhplus.be.server.product.application.mapper;

import kr.hhplus.be.server.product.application.dto.result.ProductOptionResult;
import kr.hhplus.be.server.product.application.dto.result.ProductResult;
import kr.hhplus.be.server.product.domain.entity.Product;

public class ProductResultMapper {

    public static ProductResult toResult(Product entity) {

        return new ProductResult(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getStatus(),
            entity.getOptions().stream()
                .map(option -> new ProductOptionResult(option.getId(), option.getOptionName(), option.getQuantity(), option.getPrice()))
                .toList()
        );
    }
}
