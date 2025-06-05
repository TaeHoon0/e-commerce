package kr.hhplus.be.server.user.application.port.in;

import kr.hhplus.be.server.user.application.dto.request.LoginUserCommand;
import kr.hhplus.be.server.user.application.dto.request.RegisterUserCommand;
import kr.hhplus.be.server.user.application.dto.response.LoginUserResult;
import kr.hhplus.be.server.user.application.dto.response.UserResult;

public interface UserPort {

    /**
     * 회원가입
     */
    UserResult register(RegisterUserCommand command);

    /**
     * 유저 조회
     */
    UserResult get(Long key);

    /**
     * 로그인
     */
    LoginUserResult login(LoginUserCommand command);
}
