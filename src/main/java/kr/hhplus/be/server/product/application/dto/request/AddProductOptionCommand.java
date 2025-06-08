package kr.hhplus.be.server.product.application.dto.request;

import java.util.List;

public record AddProductOptionCommand(

    long productId,
    List<ProductOptionCommand> optionCommands
) {

}
