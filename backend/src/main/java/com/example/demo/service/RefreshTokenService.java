package com.example.demo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final StringRedisTemplate redisTemplate;

    public void saveRefreshToken(String userId, String refreshToken, long durationMillis) {
        redisTemplate.opsForValue()
                .set("refresh:" + userId, refreshToken, durationMillis, TimeUnit.MILLISECONDS);
    }

    public String getRefreshToken(String userId) {
        return redisTemplate.opsForValue().get("refresh:" + userId);
    }

    public void deleteRefreshToken(String userId) {
        redisTemplate.delete("refresh:" + userId);
    }

    public boolean isValid(String userId, String refreshToken) {
        String savedToken = getRefreshToken(userId);
        return savedToken != null && savedToken.equals(refreshToken);
    }
}
