package com.example.demo.dto;

import java.sql.Timestamp;

public record PostDetailDto(
    Long id,
    String title,
    String content,
    String username,
    String nickname,
    Timestamp createdAt,
    Timestamp updatedAt
) {}