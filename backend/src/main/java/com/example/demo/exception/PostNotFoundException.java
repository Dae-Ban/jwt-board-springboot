package com.example.demo.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long postId) {
        super("존재하지 않는 게시글: " + postId);
    }
}
