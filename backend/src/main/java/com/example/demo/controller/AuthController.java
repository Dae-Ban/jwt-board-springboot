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
import com.example.demo.response.ApiResponse;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import com.example.demo.util.AccessTokenCookie;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
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

        String token = authService.login(loginRequest);
        ResponseCookie cookie = AccessTokenCookie.create(token);
        response.addHeader("Set-Cookie", cookie.toString());

        Map<String, Object> data = Map.of("message", "로그인 성공");
        return ResponseEntity.ok(ApiResponse.success(request, 200, data));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Map<String, Object>>> logout(HttpServletRequest request, HttpServletResponse response) {

        ResponseCookie deleteCookie = AccessTokenCookie.delete();
        response.addHeader("Set-Cookie", deleteCookie.toString());

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

}
