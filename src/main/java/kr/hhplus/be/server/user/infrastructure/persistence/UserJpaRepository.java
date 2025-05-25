package kr.hhplus.be.server.user.infrastructure.persistence;

import kr.hhplus.be.server.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    boolean existsByUserId(String userId);
}
