package kr.hhplus.be.server.product.presentation.mapper;

import kr.hhplus.be.server.product.application.dto.command.AddProductOptionCommand;
import kr.hhplus.be.server.product.application.dto.command.ProductOptionCommand;
import kr.hhplus.be.server.product.presentation.dto.request.AddProductOptionRequest;

public class ProductOptionRequestMapper {

    public static AddProductOptionCommand toAddProductOptionCommand(
        long productId, AddProductOptionRequest addProductOptionRequest
    ) {

        return new AddProductOptionCommand(
            productId,
            addProductOptionRequest.productOptionRequests().stream()
                .map(request -> new ProductOptionCommand(
                        null, request.optionName(), request.quantity(),request.price()
                )).toList()
        );
    }
}
