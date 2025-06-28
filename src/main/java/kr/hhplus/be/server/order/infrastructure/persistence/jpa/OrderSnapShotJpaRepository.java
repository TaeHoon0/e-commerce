package kr.hhplus.be.server.order.infrastructure.persistence.jpa;

import kr.hhplus.be.server.order.domain.order.entity.OrderSnapShot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSnapShotJpaRepository extends JpaRepository<OrderSnapShot, Long> {


}
