package kr.hhplus.be.server.user.application.usecase.in;


import jakarta.transaction.Transactional;
import kr.hhplus.be.server.user.application.mapper.UserMapper;
import kr.hhplus.be.server.user.application.port.in.UserUseCase;
import kr.hhplus.be.server.user.domain.entity.User;
import kr.hhplus.be.server.user.domain.exception.ErrorCode;
import kr.hhplus.be.server.user.domain.exception.UserException;
import kr.hhplus.be.server.user.domain.repository.UserQueryRepository;
import kr.hhplus.be.server.user.presentation.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserUseCaseImpl implements UserUseCase {

    private final UserQueryRepository userQueryRepository;

    @Override
    @Transactional
    public UserResponse register(User user) {

        // 중복검사
        if (userQueryRepository.existsByEmail(user.getEmail())) {
            throw new UserException(ErrorCode.DUPLICATE_USER_ID);
        }

        // 저장
        User savedUser = userQueryRepository.save(user);

        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserResponse get(Long key) {

        User user = userQueryRepository.findById(key)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        return UserMapper.toDto(user);
    }
}
