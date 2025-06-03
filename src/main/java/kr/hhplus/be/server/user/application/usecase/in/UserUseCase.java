package kr.hhplus.be.server.user.application.usecase.in;


import jakarta.transaction.Transactional;
import kr.hhplus.be.server.user.application.dto.request.RegisterUserCommand;
import kr.hhplus.be.server.user.application.dto.response.GetUserResult;
import kr.hhplus.be.server.user.application.dto.response.UserResult;
import kr.hhplus.be.server.user.application.mapper.UserApplicationMapper;
import kr.hhplus.be.server.user.application.port.in.UserPort;
import kr.hhplus.be.server.user.domain.entity.User;
import kr.hhplus.be.server.user.domain.exception.ErrorCode;
import kr.hhplus.be.server.user.domain.exception.UserException;
import kr.hhplus.be.server.user.domain.repository.UserCommandRepository;
import kr.hhplus.be.server.user.domain.repository.UserQueryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserUseCase implements UserPort {

    private final UserQueryRepository userQueryRepository;
    private final UserCommandRepository userCommandRepository;

    @Override
    @Transactional
    public UserResult register(RegisterUserCommand command) {

        // 중복검사
        if (userQueryRepository.existsByEmail(command.email()))
            throw new UserException(ErrorCode.DUPLICATE_USER_ID);

        // 저장
        User savedUser = userCommandRepository.save(UserApplicationMapper.toEntity(command));

        return UserApplicationMapper.toDto(savedUser);
    }

    @Override
    public UserResult get(Long key) {

        User user = userQueryRepository.findById(key)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        return UserApplicationMapper.toDto(user);
    }
}
