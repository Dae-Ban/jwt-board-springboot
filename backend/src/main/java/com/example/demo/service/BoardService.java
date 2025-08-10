package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PostListDto;
import com.example.demo.dto.PostRequest;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.repository.PostRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.util.AccessTokenCookie;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final PostRepository postRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private User currentUser(HttpServletRequest request) {
        String token = AccessTokenCookie.getToken(request, jwtTokenProvider);
        String username = jwtTokenProvider.getUsername(token);
        User user = null;
        return user;
    }

    public Page<PostListDto> findAllForList(Pageable pageable) {
        Page<PostListDto> list = postRepository.findAllForList(pageable);
        return list;
    }

    public Post findDetailById(long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new PostNotFoundException(postId));
        return post;
    }

    @Transactional
    public Long create(PostRequest req, HttpServletRequest request) {
        User user = currentUser(request);
        Post post = new Post();
        post.setTitle(req.title());
        post.setContent(req.content());
        post.setUser(user);
        Post saved = postRepository.save(post);
        return saved.getId();
    }

    

    
}
