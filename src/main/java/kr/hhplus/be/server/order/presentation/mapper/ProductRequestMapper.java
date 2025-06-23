package kr.hhplus.be.server.order.presentation.mapper;

import java.util.List;
import kr.hhplus.be.server.order.application.dto.command.ProductCommand;
import kr.hhplus.be.server.order.presentation.dto.OrderItemDto;

public class ProductRequestMapper {

    public static List<ProductCommand> toCommands(List<OrderItemDto> requests) {

        return requests.stream()
            .map(request ->
                new ProductCommand(request.productId(), request.productOptionId(), request.price(), request.quantity()))
            .toList();
    }
}
