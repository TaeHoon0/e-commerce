package kr.hhplus.be.server.product.application.dto.request;

import java.util.List;

public record DecreaseStockCommand(

        Long orderId,
        String idempotencyKey,
        List<ProductOptionCommand> items
) {
}
