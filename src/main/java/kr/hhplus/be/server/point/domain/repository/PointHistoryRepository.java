package kr.hhplus.be.server.point.domain.repository;

import kr.hhplus.be.server.point.domain.entity.PointHistory;

import java.util.List;

public interface PointHistoryRepository {

    void save(PointHistory pointHistory);

    List<PointHistory> findAllByPointId(long pointId);
}
