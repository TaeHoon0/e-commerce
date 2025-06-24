package kr.hhplus.be.server.order.domain.repository;

import kr.hhplus.be.server.order.domain.order.entity.OrderSnapShot;

public interface OrderSnapShotCommandRepository {

    void save(OrderSnapShot orderSnapShot);
}
