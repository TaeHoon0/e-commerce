package kr.hhplus.be.server.global.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.hhplus.be.server.global.dto.ApiResult;
import kr.hhplus.be.server.global.dto.StatusCode;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {

        response.setStatus(StatusCode.UNAUTHORIZED.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ApiResult<Void> error = ApiResult.error(StatusCode.UNAUTHORIZED.getCode(), StatusCode.UNAUTHORIZED.getMessage());

        response.getWriter().write(objectMapper.writeValueAsString(error));
    }
}