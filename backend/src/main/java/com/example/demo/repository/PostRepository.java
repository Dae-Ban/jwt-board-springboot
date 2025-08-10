package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.PostDetailDto;
import com.example.demo.dto.PostListDto;
import com.example.demo.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
        select new com.example.board.dto.PostListDto(
            p.id, p.title, u.nickname, p.created_at
        )
        from Post p
        join p.user u
        """)
    Page<PostListDto> findAllForList(Pageable pageable);

    @Query("""
    select new com.example.board.dto.PostDetailDto(
        p.id, p.title, p.content, u.username, u.nickname ,p.created_at, p.updated_at
    )
    from Post p
    join p.user u
    where p.id = :postId
    """)
    Optional<PostDetailDto> findDetailById(Long postId);

    
}
