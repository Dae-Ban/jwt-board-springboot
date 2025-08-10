package com.example.demo.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PostDetailDto;
import com.example.demo.dto.PostListDto;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<PostListDto>>> list(@PageableDefault(
        size = 20,
        page = 1,
        sort = "id",
        direction = Sort.Direction.DESC
    ) Pageable pageable, HttpServletRequest request) {
        Page<PostListDto> page = boardService.findAllForList(pageable);
        return ResponseEntity.ok(ApiResponse.success(request, 200, page));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostDetailDto>> read(@PathVariable long postId, HttpServletRequest request) {
        PostDetailDto post = boardService.findDetailById(postId);
        return ResponseEntity.ok(ApiResponse.success(request, 200, post));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<Map<String, Object>>> write() {
        return null;
    }

    @PutMapping("/{postId}")
    public ResponseEntity edit(@PathVariable String postId) {
        return null;
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity delete(@PathVariable String postId) {
        return null;
    }
}
