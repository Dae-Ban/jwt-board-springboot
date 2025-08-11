package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.PostDetailDto;
import com.example.demo.dto.PostListDto;
import com.example.demo.dto.PostRequest;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.exception.ForbiddenException;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;;

    public Page<PostListDto> findAllForList(Pageable pageable) {
        Page<PostListDto> list = postRepository.findAllForList(pageable);
        return list;
    }

    public PostDetailDto findDetailById(long postId) {
        PostDetailDto post = postRepository.findDetailById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        return post;
    }

    @Transactional
    public Long create(PostRequest req, long userId) {
        User userRef = userRepository.getReferenceById(userId); 
        Post post = new Post();
        post.setTitle(req.title());
        post.setContent(req.content());
        post.setUser(userRef);
        Post saved = postRepository.save(post);
        return saved.getId();
    }

    @Transactional
    public void update(long postId, PostRequest req, long userId) {
        User userRef = userRepository.getReferenceById(userId); 
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));

        // 작성자만 수정 가능
        if (!post.getUser().getId().equals(userRef.getId())) {
            throw new ForbiddenException("수정 권한이 없습니다.");
        }

        post.setTitle(req.title());
        post.setContent(req.content());
        // flush
    }

    @Transactional
    public void delete(long postId, long userId) {
        User userRef = userRepository.getReferenceById(userId); 
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));

        // 작성자만 삭제 가능
        if (!post.getUser().getId().equals(userRef.getId())) {
            throw new ForbiddenException("삭제 권한이 없습니다.");
        }

        postRepository.delete(post);
    }

}
