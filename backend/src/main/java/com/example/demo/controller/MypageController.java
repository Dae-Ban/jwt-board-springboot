package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserInfo;
import com.example.demo.dto.UserPrincipal;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.MypageService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<UserInfo>> myInfo(@AuthenticationPrincipal UserPrincipal principal,
            HttpServletRequest request) {
        UserInfo userInfo = mypageService.myInfo(principal.getId());
        return ResponseEntity.ok(ApiResponse.success(request, 200, userInfo));
    }

    @PatchMapping("/nickname")
    public ResponseEntity<ApiResponse<Map<String, Object>>> updateNickname(
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestBody Map<String, String> body,
            HttpServletRequest request) {
        mypageService.updateNickname(principal.getId(), body.get("nickname"));

        Map<String, Object> data = Map.of("message", "닉네임 변경 완료");
        return ResponseEntity.ok(ApiResponse.success(request, 200, data));
    }

    @PatchMapping("/password")
    public ResponseEntity<ApiResponse<Map<String, Object>>> updatePassword(
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestBody Map<String, String> body,
            HttpServletRequest request) {
        mypageService.updatePassword(principal.getId(), body.get("password"));

        Map<String, Object> data = Map.of("message", "비밀번호 변경 완료");
        return ResponseEntity.ok(ApiResponse.success(request, 200, data));
    }
}
