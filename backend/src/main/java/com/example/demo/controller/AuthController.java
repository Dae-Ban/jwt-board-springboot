package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.exception.InvalidTokenException;
import com.example.demo.response.ApiResponse;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import com.example.demo.service.RefreshTokenService;
import com.example.demo.util.AccessTokenCookie;
import com.example.demo.util.RefreshTokenCookie;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Map<String, Object>>> register(@RequestBody RegisterRequest registerRequest,
            HttpServletRequest request) {
        authService.register(registerRequest);

        Map<String, Object> data = Map.of("message", "회원가입 성공");
        return ResponseEntity.ok(ApiResponse.success(request, 200, data));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(@RequestBody LoginRequest loginRequest,
            HttpServletRequest request, HttpServletResponse response) {

        // 인증
        User user = authService.login(loginRequest);

        // 토큰 생성
        String accessToken = jwtTokenProvider.createToken(user.getUsername());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUsername());

        // Redis 저장
        refreshTokenService.saveRefreshToken(user.getUsername(), refreshToken, 7 * 24 * 60 * 60 * 1000L);

        // 쿠키로 전송
        response.addHeader("Set-Cookie", AccessTokenCookie.create(accessToken).toString());
        response.addHeader("Set-Cookie", RefreshTokenCookie.create(refreshToken).toString());

        Map<String, Object> data = Map.of("message", "로그인 성공");
        return ResponseEntity.ok(ApiResponse.success(request, 200, data));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Map<String, Object>>> logout(HttpServletRequest request,
            HttpServletResponse response) {

        response.addHeader("Set-Cookie", AccessTokenCookie.delete().toString());
        response.addHeader("Set-Cookie", RefreshTokenCookie.delete().toString());

        try {
            String accessToken = AccessTokenCookie.getToken(request, jwtTokenProvider);
            String username = jwtTokenProvider.getUsername(accessToken);
            refreshTokenService.deleteRefreshToken(username);
        } catch (Exception e) {
            // 무시: 토큰이 없어도 로그아웃은 허용
        }

        Map<String, Object> data = Map.of("message", "로그아웃 완료");
        return ResponseEntity.ok(ApiResponse.success(request, 200, data));
    }

    @GetMapping("/check")
    public ResponseEntity<ApiResponse<Map<String, Object>>> checkLogin(HttpServletRequest request) {

        String token = AccessTokenCookie.getToken(request, jwtTokenProvider);
        String username = jwtTokenProvider.getUsername(token);

        Map<String, Object> data = Map.of(
                "message", "인증 성공",
                "isLoggedIn", true,
                "username", username);
        return ResponseEntity.ok(ApiResponse.success(request, 200, data));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<Map<String, Object>>> refresh(HttpServletRequest request,
            HttpServletResponse response) {

        String refreshToken = RefreshTokenCookie.getToken(request, jwtTokenProvider);

        // 1. 유효성 검사
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new InvalidTokenException("Invalid refresh token");
        }

        // 2. 사용자 ID 추출
        String userId = jwtTokenProvider.getUsername(refreshToken);

        // 3. Redis에서 가져온 토큰과 일치하는지 확인
        if (!refreshTokenService.isValid(userId, refreshToken)) {
            throw new InvalidTokenException("Token mismatch");
        }

        // 4. 새 AccessToken 발급
        String newAccessToken = jwtTokenProvider.createToken(userId);
        ResponseCookie cookie = AccessTokenCookie.create(newAccessToken);
        response.addHeader("Set-Cookie", cookie.toString());

        Map<String, Object> data = Map.of("message", "로그인 성공");
        return ResponseEntity.ok(ApiResponse.success(request, 200, data));
    }

}
