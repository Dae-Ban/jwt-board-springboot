package com.example.demo.controller;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Map<String, Object>>> register(@RequestBody RegisterRequest registerRequest,
            HttpServletRequest request) {
        authService.register(registerRequest);
        Map<String, Object> data = new HashMap<>();
        data.put("message", "회원가입 성공");
        return ResponseEntity.ok(ApiResponse.success(request, 200, data));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(@RequestBody LoginRequest loginRequest,
            HttpServletRequest request, HttpServletResponse response) {
        String token = authService.login(loginRequest);

        boolean isLocal = request.getServerName().contains("localhost");
        ResponseCookie cookie = ResponseCookie.from("accessToken", token)
                .httpOnly(true)
                .secure(!isLocal)  // http 로컬만 허용
                .path("/")
                .sameSite("None")   // 리버스 프록시 설정 후 Strict
                .maxAge(Duration.ofHours(1))
                .build();
        response.addHeader("Set-Cookie", cookie.toString());

        Map<String, Object> data = new HashMap<>();
        data.put("message", "로그인 성공");

        return ResponseEntity.ok(ApiResponse.success(request, 200, data));
    }

}
