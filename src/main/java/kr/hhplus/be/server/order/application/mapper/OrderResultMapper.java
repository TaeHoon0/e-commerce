package kr.hhplus.be.server.order.application.mapper;

import kr.hhplus.be.server.order.application.dto.result.CreateOrderResult;
import kr.hhplus.be.server.order.domain.order.entity.Order;

public class OrderResultMapper {

    public static CreateOrderResult toResult(Order order) {

        return new CreateOrderResult(
            order.getId(),
            order.getUserId(),
            order.getDiscountPrice(),
            order.getFinalPrice(),
            order.getTotalPrice(),
            order.getStatus()
        );
    }
}
