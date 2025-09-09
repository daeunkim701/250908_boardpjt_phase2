package com.example.boardpjt.model.repository;

import com.example.boardpjt.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // JpaRepository<엔티티 클래스, Primary Key 타입>
    // - Post: 관리할 엔티티 클래스
    // - Long: Primary Key(id 필드)의 데이터 타입

    // find
    // By -> Id
    // Title -> Containing / SQL like -> %키워드%
    // or Content -> Containing
    Page<Post> findByTitleContainingOrContentContaining(
            String title, String content, Pageable pageable);

    // 최신 순으로 (내림차순)
    Page<Post> findByTitleContainingOrContentContainingOrderByIdDesc(
            String title, String content, Pageable pageable);
    // Desc -> PK (Long id)
}
