package com.example.demo.response;

import java.time.LocalDateTime;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
	private LocalDateTime timestamp;
	private int statusCode;
	private String method;
	private String path;
	private boolean success;
	private String message;
	private T data;

	private ApiResponse(LocalDateTime timestamp, int statusCode, String method, String path, boolean success, String message, T data) {
		this.timestamp = timestamp;
		this.statusCode = statusCode;
		this.method = method;
		this.path = path;
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public static <T> ApiResponse<T> success(HttpServletRequest request, int statusCode, T data) {
		return new ApiResponse<>(LocalDateTime.now(), statusCode, request.getMethod(), request.getRequestURI(), true, "요청이 성공했습니다.", data);
	}

	public static <T> ApiResponse<T> failure(HttpServletRequest request, int statusCode, String message) {
		return new ApiResponse<>(LocalDateTime.now(), statusCode, request.getMethod(), request.getRequestURI(), false, message, null);
	}

}