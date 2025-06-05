package kr.hhplus.be.server.point.domain.repository;

import kr.hhplus.be.server.point.domain.entity.PointHistory;

public interface PointHistoryCommandRepository {

    void save(PointHistory pointHistory);

}
