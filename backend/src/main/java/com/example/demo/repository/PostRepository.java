package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.PostDetailDto;
import com.example.demo.dto.PostListDto;
import com.example.demo.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAll(Pageable pageable);

    @Query(value = """
            select new com.example.demo.dto.PostListDto(
                p.id, p.title, p.user.nickname, p.createdAt
            )
            from Post p
            """)
    Page<PostListDto> findAllForList(Pageable pageable);

    @Query("""
              select new com.example.demo.dto.PostDetailDto(
                p.id, p.title, p.content, u.username, u.nickname, p.createdAt, p.updatedAt
              )
              from Post p
              join p.user u
              where p.id = :postId
            """)
    Optional<PostDetailDto> findDetailById(@Param("postId") long postId);

}
