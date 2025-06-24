package kr.hhplus.be.server.order.infrastructure.persistence;

import kr.hhplus.be.server.order.domain.order.entity.OrderSnapShot;
import kr.hhplus.be.server.order.domain.repository.OrderSnapShotCommandRepository;
import kr.hhplus.be.server.order.infrastructure.persistence.jpa.OrderSnapShotJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderSnapShotRepositoryImpl implements OrderSnapShotCommandRepository {

    private final OrderSnapShotJpaRepository orderSnapShotJpaRepository;

    @Override
    public void save(OrderSnapShot orderSnapShot) {

        orderSnapShotJpaRepository.save(orderSnapShot);
    }
}
