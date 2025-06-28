package kr.hhplus.be.server.product.presentation.mapper;

import kr.hhplus.be.server.product.application.dto.result.ProductResult;
import kr.hhplus.be.server.product.presentation.dto.response.ProductOptionResponse;
import kr.hhplus.be.server.product.presentation.dto.response.ProductResponse;

public class ProductResponseMapper {

    public static ProductResponse toRegisterResponse(ProductResult result) {

        return new ProductResponse(
            result.id(),
            result.name(),
            result.description(),
            result.status(),
            null
        );
    }

    public static ProductResponse toResponse(ProductResult result) {

        return new ProductResponse(
            result.id(),
            result.name(),
            result.description(),
            result.status(),
            result.options().stream().map(option -> new ProductOptionResponse(
                option.id(), option.optionName(), option.quantity(), option.price()
            )).toList()
        );
    }
}
