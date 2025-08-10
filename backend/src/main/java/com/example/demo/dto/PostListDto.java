package com.example.demo.dto;

import java.sql.Timestamp;

public record PostListDto(
    Long id,
    String title,
    String nickname,
    Timestamp createdAt
) {}