package kr.hhplus.be.server.order.presentation.dto.request;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import kr.hhplus.be.server.order.presentation.dto.CouponDto;
import kr.hhplus.be.server.order.presentation.dto.OrderItemDto;
import kr.hhplus.be.server.order.presentation.dto.PointDto;

public record CreateOrderRequest(

    CouponDto coupon,
    PointDto point,

    @NotEmpty
    List<OrderItemDto> items
) {
}
