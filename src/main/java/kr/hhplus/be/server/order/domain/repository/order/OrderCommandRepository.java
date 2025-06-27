package kr.hhplus.be.server.order.domain.repository.order;

import kr.hhplus.be.server.order.domain.order.entity.Order;

public interface OrderCommandRepository {

    Order save(Order order);
}
