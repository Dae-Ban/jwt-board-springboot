package com.example.demo.security.handler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginFailureHandler {

    private final ObjectMapper objectMapper;

    public void loginFailed(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
            throws IOException {
        String message;
        if (ex instanceof UsernameNotFoundException) {
            message = "아이디 틀림";
        } else if (ex instanceof BadCredentialsException) {
            message = "비밀번호 틀림";
        } else if (ex instanceof LockedException) {
            message = "계정이 잠겼습니다.";
        } else if (ex instanceof DisabledException) {
            message = "계정이 비활성화되었습니다.";
        } else {
            message = "로그인에 실패했습니다.";
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(
                ApiResponse.failure(request, HttpStatus.UNAUTHORIZED.value(), message)));
    }
}
