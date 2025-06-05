package kr.hhplus.be.server.product.presentation.dto.response;

import kr.hhplus.be.server.product.domain.ProductStatus;

import java.util.List;

public record ProductResponse (

        Long id,
        String name,
        String description,
        ProductStatus status,
        List<ProductOptionResponse> options
){

}