package kr.hhplus.be.server.user.infrastructure.persistence.jpa;

import kr.hhplus.be.server.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
