package kr.hhplus.be.server.product.application.dto.command;

import java.util.List;

public record AddProductOptionCommand(

    long productId,
    List<ProductOptionCommand> optionCommands
) {

}
