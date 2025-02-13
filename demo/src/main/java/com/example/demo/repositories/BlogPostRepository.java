package com.example.demo.repositories;

import com.example.demo.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    // La paginazione è automaticamente supportata grazie a JpaRepository
    Page<BlogPost> findAll(Pageable pageable);
} 