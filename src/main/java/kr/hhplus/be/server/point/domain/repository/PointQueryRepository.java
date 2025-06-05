package kr.hhplus.be.server.point.domain.repository;

import kr.hhplus.be.server.point.domain.entity.Point;

import java.util.Optional;

public interface PointQueryRepository {

    Optional<Point> findByUserIdWithLock(Long userId);

    Optional<Point> findByUserId(Long userId);

}
