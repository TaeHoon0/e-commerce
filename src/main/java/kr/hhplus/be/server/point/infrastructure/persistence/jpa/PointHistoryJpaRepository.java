package kr.hhplus.be.server.point.infrastructure.persistence.jpa;

import kr.hhplus.be.server.point.domain.entity.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointHistoryJpaRepository extends JpaRepository<PointHistory, Long> {

    List<PointHistory> findAllByPoint_IdOrderByIdAsc(long pointKey);
}
