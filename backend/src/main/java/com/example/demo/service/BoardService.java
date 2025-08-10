package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PostDetailDto;
import com.example.demo.dto.PostListDto;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final PostRepository postRepository;

    public Page<PostListDto> findAllForList(Pageable pageable) {
        return postRepository.findAllForList(pageable);
    }

    public PostDetailDto findDetailById(long postId) {
        PostDetailDto post = postRepository.findDetailById(postId)
            .orElseThrow(() -> new PostNotFoundException(postId));
        return post;
    }

    
}
