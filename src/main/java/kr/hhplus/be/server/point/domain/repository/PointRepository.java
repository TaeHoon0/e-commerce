package kr.hhplus.be.server.point.domain.repository;

import kr.hhplus.be.server.point.domain.entity.Point;

import java.util.Optional;

public interface PointRepository {

    Optional<Point> findByUserId(Long userId);

    Point save(Point point);
}
