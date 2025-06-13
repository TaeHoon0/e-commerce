package kr.hhplus.be.server.point.infrastructure.persistence;

import kr.hhplus.be.server.point.domain.entity.PointHistory;
import kr.hhplus.be.server.point.domain.repository.PointHistoryCommandRepository;
import kr.hhplus.be.server.point.domain.repository.PointHistoryQueryRepository;
import kr.hhplus.be.server.point.infrastructure.persistence.jpa.PointHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PointHistoryRepositoryImpl implements PointHistoryCommandRepository, PointHistoryQueryRepository {

    private final PointHistoryJpaRepository jpaRepository;

    @Override
    public void save(PointHistory pointHistory) {

        jpaRepository.save(pointHistory);
    }
}
