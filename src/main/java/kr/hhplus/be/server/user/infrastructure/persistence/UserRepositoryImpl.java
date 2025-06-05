package kr.hhplus.be.server.user.infrastructure.persistence;

import kr.hhplus.be.server.user.domain.entity.User;
import kr.hhplus.be.server.user.domain.repository.UserCommandRepository;
import kr.hhplus.be.server.user.domain.repository.UserQueryRepository;
import kr.hhplus.be.server.user.infrastructure.persistence.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserQueryRepository, UserCommandRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public User save(User user) {
        return jpaRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {

        return jpaRepository.findByEmail(email);
    }
}

