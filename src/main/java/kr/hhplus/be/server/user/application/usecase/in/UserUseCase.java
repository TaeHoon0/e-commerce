package kr.hhplus.be.server.user.application.usecase.in;


import kr.hhplus.be.server.global.config.jwt.JwtUtil;
import kr.hhplus.be.server.user.application.dto.request.LoginUserCommand;
import kr.hhplus.be.server.user.application.dto.request.RegisterUserCommand;
import kr.hhplus.be.server.user.application.dto.response.LoginUserResult;
import kr.hhplus.be.server.user.application.dto.response.UserResult;
import kr.hhplus.be.server.user.application.mapper.UserResultMapper;
import kr.hhplus.be.server.user.application.port.in.UserPort;
import kr.hhplus.be.server.user.application.port.out.PasswordEncoderPort;
import kr.hhplus.be.server.user.domain.entity.User;
import kr.hhplus.be.server.user.domain.exception.UserErrorCode;
import kr.hhplus.be.server.user.domain.exception.UserException;
import kr.hhplus.be.server.user.domain.repository.UserCommandRepository;
import kr.hhplus.be.server.user.domain.repository.UserQueryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserUseCase implements UserPort {

    private final UserQueryRepository userQueryRepository;
    private final UserCommandRepository userCommandRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public UserResult register(RegisterUserCommand command) {

        // 중복 검사
        if(userQueryRepository.findByEmail(command.email()).isPresent())
            throw new UserException(UserErrorCode.DUPLICATE_USER_EMAIL);

        // 저장
        User user = User.create(command.email(), passwordEncoder.encode(command.password()), command.name(), command.type());

        user = userCommandRepository.save(user);

        return UserResultMapper.toResult(user);
    }

    @Override
    public UserResult get(Long key) {

        User user = userQueryRepository.findById(key)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserResultMapper.toResult(user);
    }

    @Override
    @Transactional
    public LoginUserResult login(LoginUserCommand command) {

        User user = userQueryRepository.findByEmail(command.email())
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        if(!passwordEncoder.matches(command.password(), user.getPassword()))
            throw new UserException(UserErrorCode.NOT_MATCHED_PASSWORD);

        user.updateLoginDate();

        String accessToken = jwtUtil.createAccessToken(user.getId(), user.getEmail(), user.getType().name());
        String refreshToken = jwtUtil.createRefreshToken(user.getId(), user.getEmail(), user.getType().name());

        return new LoginUserResult(accessToken, refreshToken);
    }
}
