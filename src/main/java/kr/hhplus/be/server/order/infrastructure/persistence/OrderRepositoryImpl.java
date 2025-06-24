package kr.hhplus.be.server.order.infrastructure.persistence;

import kr.hhplus.be.server.order.domain.order.entity.Order;
import kr.hhplus.be.server.order.domain.repository.OrderCommandRepository;
import kr.hhplus.be.server.order.domain.repository.OrderQueryRepository;
import kr.hhplus.be.server.order.infrastructure.persistence.jpa.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderCommandRepository, OrderQueryRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {

        return orderJpaRepository.save(order);
    }

    @Override
    public Optional<Order> findByIdempotencyKey(String idempotencyKey) {

        return orderJpaRepository.findByIdempotencyKey(idempotencyKey);
    }
}
