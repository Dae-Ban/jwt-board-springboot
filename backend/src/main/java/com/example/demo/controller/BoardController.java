package com.example.demo.controller;

import java.net.URI;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PostDetailDto;
import com.example.demo.dto.PostListDto;
import com.example.demo.dto.PostRequest;
import com.example.demo.dto.UserPrincipal;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<PostListDto>>> list(
            @PageableDefault(size = 20, page = 0, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            HttpServletRequest request) {
        Page<PostListDto> page = boardService.findAllForList(pageable);
        return ResponseEntity.ok(ApiResponse.success(request, 200, page));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostDetailDto>> read(@PathVariable long postId, HttpServletRequest request) {
        PostDetailDto post = boardService.findDetailById(postId);
        return ResponseEntity.ok(ApiResponse.success(request, 200, post));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<Map<String, Object>>> write(@AuthenticationPrincipal UserPrincipal principal,
            @Valid @RequestBody PostRequest postRequest,
            HttpServletRequest request) {
        Long id = boardService.create(postRequest, principal.getId());
        Map<String, Object> data = Map.of("message", id + " 업로드 완료");
        URI location = URI.create("/board/" + id);
        return ResponseEntity.created(location)
                .body(ApiResponse.success(request, 201, data));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse<Map<String, Object>>> edit(@AuthenticationPrincipal UserPrincipal principal,
            @PathVariable long postId,
            @Valid @RequestBody PostRequest postRequest,
            HttpServletRequest request) {
        boardService.update(postId, postRequest, principal.getId());
        Map<String, Object> data = Map.of("message", postId + " 수정완료");
        return ResponseEntity.ok(ApiResponse.success(request, 200, data));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal UserPrincipal principal, @PathVariable long postId,
            HttpServletRequest request) {
        boardService.delete(postId, principal.getId());
        return ResponseEntity.noContent().build();
    }
}
