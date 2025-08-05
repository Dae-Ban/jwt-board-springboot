package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserInfo;
import com.example.demo.response.ApiResponse;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.MypageService;
import com.example.demo.util.AccessTokenCookie;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("")
    public ResponseEntity<ApiResponse<UserInfo>> myInfo(HttpServletRequest request) {
        String token = AccessTokenCookie.getToken(request, jwtTokenProvider);
        String username = jwtTokenProvider.getUsername(token);

        UserInfo userInfo = mypageService.myInfo(username);
        return ResponseEntity.ok(ApiResponse.success(request, 200, userInfo));
    }

    @PatchMapping("/nickname")
    public ResponseEntity<ApiResponse<Map<String, Object>>> updateNickname(@RequestBody Map<String, String> body, HttpServletRequest request) {
        String token = AccessTokenCookie.getToken(request, jwtTokenProvider);
        String username = jwtTokenProvider.getUsername(token);

        mypageService.updateNickname(username, body.get("nickname"));

        Map<String, Object> data = Map.of("message", "닉네임 변경 완료");
        return ResponseEntity.ok(ApiResponse.success(request, 200, data));
    }

    @PatchMapping("/password")
    public ResponseEntity<ApiResponse<Map<String, Object>>> updatePassword(@RequestBody Map<String, String> body, HttpServletRequest request) {
        String token = AccessTokenCookie.getToken(request, jwtTokenProvider);
        String username = jwtTokenProvider.getUsername(token);

        mypageService.updateNickname(username, body.get("password"));

        Map<String, Object> data = Map.of("message", "비밀번호 변경 완료");
        return ResponseEntity.ok(ApiResponse.success(request, 200, data));
    }
}
