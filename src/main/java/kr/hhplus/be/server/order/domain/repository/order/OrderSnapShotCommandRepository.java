package kr.hhplus.be.server.order.domain.repository.order;

import kr.hhplus.be.server.order.domain.order.entity.OrderSnapShot;

public interface OrderSnapShotCommandRepository {

    void save(OrderSnapShot orderSnapShot);
}
