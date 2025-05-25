package kr.hhplus.be.server.user.application.port.in;

import kr.hhplus.be.server.user.domain.entity.User;
import kr.hhplus.be.server.user.presentation.dto.response.UserResponseDto;

public interface UserUseCase {

    /**
     * 회원가입
     * @param user
     */
    UserResponseDto register(User user);

    /**
     * 유저 조회
     */
    UserResponseDto get(Long key);
}
