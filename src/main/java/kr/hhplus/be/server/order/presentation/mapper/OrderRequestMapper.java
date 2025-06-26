package kr.hhplus.be.server.order.presentation.mapper;

import java.util.List;
import java.util.Optional;
import kr.hhplus.be.server.order.application.dto.command.ApproveOrderCommand;
import kr.hhplus.be.server.order.application.dto.command.CouponCommand;
import kr.hhplus.be.server.order.application.dto.command.CreateOrderCommand;
import kr.hhplus.be.server.order.application.dto.command.PointCommand;
import kr.hhplus.be.server.order.application.dto.command.ProductCommand;
import kr.hhplus.be.server.order.presentation.dto.request.ApproveOrderRequest;
import kr.hhplus.be.server.order.presentation.dto.request.CreateOrderRequest;

public class OrderRequestMapper {

    public static CreateOrderCommand toCreateCommand(
        long userId, String idempotencyKey, CreateOrderRequest request
    ) {

        CouponCommand couponCommand = Optional.ofNullable(request.coupon())
            .map(CouponRequestMapper::toCommand)
            .orElse(null);

        PointCommand pointCommand = Optional.ofNullable(request.point())
            .map(PointRequestMapper::toCommand)
            .orElse(null);

        List<ProductCommand> productCommands = ProductRequestMapper.toCommands(request.items());

        return new CreateOrderCommand(userId, idempotencyKey, couponCommand, pointCommand, productCommands);
    }

    public static ApproveOrderCommand toCommand(
        long userId, ApproveOrderRequest request
    ) {

        return new ApproveOrderCommand(
            userId,
            request.orderId(),
            request.tid()
        );
    }
}
