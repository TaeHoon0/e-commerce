package kr.hhplus.be.server.user.application.port.in;

import kr.hhplus.be.server.user.application.dto.command.LoginUserCommand;
import kr.hhplus.be.server.user.application.dto.command.RegisterUserCommand;
import kr.hhplus.be.server.user.application.dto.result.LoginUserResult;
import kr.hhplus.be.server.user.application.dto.result.UserResult;

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
