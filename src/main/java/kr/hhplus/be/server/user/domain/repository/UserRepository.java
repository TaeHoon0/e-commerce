package kr.hhplus.be.server.user.domain.repository;

import kr.hhplus.be.server.user.domain.entity.User;

import java.util.Optional;

public interface UserRepository {

    boolean existsByEmail(String userId);

    User save(User user);

    Optional<User> findById(Long id);
}
