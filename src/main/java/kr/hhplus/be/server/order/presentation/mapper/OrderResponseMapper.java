package kr.hhplus.be.server.order.presentation.mapper;

import kr.hhplus.be.server.order.application.dto.result.ApproveOrderResult;
import kr.hhplus.be.server.order.application.dto.result.CreateOrderResult;
import kr.hhplus.be.server.order.presentation.dto.response.ApproveOrderResponse;
import kr.hhplus.be.server.order.presentation.dto.response.CreateOrderResponse;

public class OrderResponseMapper {

    public static CreateOrderResponse toResponse(CreateOrderResult result) {

        return new CreateOrderResponse(
            result.orderId(),
            result.userId(),
            result.discountPrice(),
            result.finalPrice(),
            result.totalPrice(),
            result.status()
        );
    }

    public static ApproveOrderResponse toResponse(ApproveOrderResult result) {

        return new ApproveOrderResponse(
            result.orderId(),
            result.totalPrice(),
            result.discountPrice(),
            result.finalPrice()
        );
    }
}
