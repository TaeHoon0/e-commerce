package kr.hhplus.be.server.user.domain.repository;

import kr.hhplus.be.server.user.domain.entity.User;

import java.util.Optional;

public interface UserQueryRepository {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
