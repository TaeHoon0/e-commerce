package kr.hhplus.be.server.product.presentation.mapper;

import kr.hhplus.be.server.product.application.dto.request.RegisterProductCommand;
import kr.hhplus.be.server.product.presentation.dto.request.ProductRequest;

public class ProductRequestMapper {

    public static RegisterProductCommand toRegisterCommand(ProductRequest request) {

        return new RegisterProductCommand(
            request.name(),
            request.description()
        );
    }
}
