package kr.hhplus.be.server.product.application.dto.command;

import java.util.List;
import kr.hhplus.be.server.product.application.dto.ProductOptionDto;

public record DecreaseStockCommand(

        Long orderId,
        String idempotencyKey,
        List<ProductOptionDto> items
) {
}
