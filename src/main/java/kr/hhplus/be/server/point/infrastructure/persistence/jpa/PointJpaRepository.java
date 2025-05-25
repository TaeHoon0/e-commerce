package kr.hhplus.be.server.point.infrastructure.persistence.jpa;

import kr.hhplus.be.server.point.domain.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointJpaRepository extends JpaRepository<Point, Long> {

    Optional<Point> findByUserId(Long userId);

}
