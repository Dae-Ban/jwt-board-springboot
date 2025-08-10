package com.example.demo.util;

import java.time.Duration;

import org.springframework.http.ResponseCookie;

import com.example.demo.security.JwtTokenProvider;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class AccessTokenCookie {

    public static String getToken(HttpServletRequest request, JwtTokenProvider jwtTokenProvider) {
        String token = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        jwtTokenProvider.validateToken(token);

        return token;
    }

    public static ResponseCookie create(String token) {

        ResponseCookie cookie = ResponseCookie.from("accessToken", token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("None") // 리버스 프록시 설정 후 Strict
                .maxAge(Duration.ofMinutes(15))
                .build();
        return cookie;
    }

    public static ResponseCookie delete() {
        ResponseCookie deleteCookie = ResponseCookie.from("accessToken", "")
                .path("/")
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .maxAge(0) // 쿠키 즉시 만료
                .build();
        return deleteCookie;
    }
}