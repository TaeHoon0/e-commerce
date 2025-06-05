package kr.hhplus.be.server.point.infrastructure.persistence;

import kr.hhplus.be.server.point.domain.entity.Point;
import kr.hhplus.be.server.point.domain.repository.PointCommandRepository;
import kr.hhplus.be.server.point.domain.repository.PointQueryRepository;
import kr.hhplus.be.server.point.infrastructure.persistence.jpa.PointJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PointRepositoryImpl implements PointCommandRepository, PointQueryRepository {

    private final PointJpaRepository jpaRepository;

    @Override
    public Optional<Point> findByUserIdWithLock(Long userId) {
        return jpaRepository.findByUserIdWithLock(userId);
    }

    @Override
    public Optional<Point> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId);
    }

    @Override
    public Point save(Point point) {
        return jpaRepository.save(point);
    }
}
