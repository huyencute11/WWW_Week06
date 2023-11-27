package com.example.www_week06.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.www_week06.models.Post;
import com.example.www_week06.models.PostModel.PostListModel;
import com.example.www_week06.models.PostModel.PostModel;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT new com.example.www_week06.models.PostModel.PostListModel(p) FROM Post p")
    Page<PostListModel> findAllAsDto(Pageable pageable);
}
