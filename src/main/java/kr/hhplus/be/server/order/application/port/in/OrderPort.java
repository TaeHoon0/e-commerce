package kr.hhplus.be.server.order.application.port.in;

import kr.hhplus.be.server.order.application.dto.command.CreateOrderCommand;
import kr.hhplus.be.server.order.application.dto.result.CreateOrderResult;

public interface OrderPort {

    /**
     * 주문 생성
     */
    CreateOrderResult createOrder(CreateOrderCommand command);

}
