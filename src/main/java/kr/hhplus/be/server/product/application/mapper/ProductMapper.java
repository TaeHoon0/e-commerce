package kr.hhplus.be.server.product.application.mapper;

import kr.hhplus.be.server.product.domain.entity.Product;
import kr.hhplus.be.server.product.domain.entity.ProductOption;
import kr.hhplus.be.server.product.presentation.dto.request.ProductOptionRequest;
import kr.hhplus.be.server.product.presentation.dto.request.ProductRequest;
import kr.hhplus.be.server.product.presentation.dto.response.ProductOptionResponse;
import kr.hhplus.be.server.product.presentation.dto.response.ProductResponse;

public class ProductMapper {

    public static ProductResponse toDto(Product entity) {

        return new ProductResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getProductOptions().stream().map(ProductMapper::toDto).toList()
        );
    }

    public static ProductOptionResponse toDto(ProductOption entity) {

        return new ProductOptionResponse(
                entity.getKey(),
                entity.getOptionName(),
                entity.getQuantity(),
                entity.getPrice()
        );
    }

    public static Product toEntity(ProductRequest request) {

        return Product.createNew(
                request.name(),
                request.description()
        );
    }

    public static ProductOption toEntity(ProductOptionRequest.OptionRequest request) {

        return ProductOption.createNew(
                request.optionName(),
                request.quantity(),
                request.price()
        );
    }
}
