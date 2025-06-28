package kr.hhplus.be.server.order.domain.repository.order;

import kr.hhplus.be.server.order.domain.order.entity.Order;

import java.util.Optional;

public interface OrderQueryRepository {

    Optional<Order> findByIdempotencyKey(String idempotencyKey);

    Optional<Order> findById(long id);
}
