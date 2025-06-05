package kr.hhplus.be.server.user.domain.repository;

import kr.hhplus.be.server.user.domain.entity.User;

public interface UserCommandRepository {

    User save(User user);
}
