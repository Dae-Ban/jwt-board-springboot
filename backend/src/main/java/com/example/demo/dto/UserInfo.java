package com.example.demo.dto;

import java.sql.Timestamp;

import com.example.demo.entity.User;

import lombok.Data;

@Data
public class UserInfo {
    private String username;
    private String nickname;
    private String role;
    private Timestamp created_at;

    public UserInfo(User user) {
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.role = user.getRole();
        this.created_at = user.getCreated_at();
    }
}
