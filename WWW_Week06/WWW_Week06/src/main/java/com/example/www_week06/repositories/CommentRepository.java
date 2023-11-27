package com.example.www_week06.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.www_week06.models.PostComment;

public interface CommentRepository extends JpaRepository<PostComment, Long> {
    
}
