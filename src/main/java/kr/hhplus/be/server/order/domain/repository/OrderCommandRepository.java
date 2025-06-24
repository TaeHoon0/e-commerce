package kr.hhplus.be.server.order.domain.repository;

import kr.hhplus.be.server.order.domain.order.entity.Order;

public interface OrderCommandRepository {

    Order save(Order order);
}
