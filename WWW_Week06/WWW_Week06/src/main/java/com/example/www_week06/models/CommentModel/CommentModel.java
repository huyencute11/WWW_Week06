package com.example.www_week06.models.CommentModel;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import com.example.www_week06.models.Post;
import com.example.www_week06.models.PostComment;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class CommentModel {
    private long id;
    private String userName;
    private Long userId;
    private String title;
    private int published;
    private LocalDate publishedAt;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String content;
    public CommentModel(PostComment comment) {
        this.id = comment.getId();
        this.userName = comment.getUser().getFirstName();
        this.userId = comment.getUser().getId();
        this.title = comment.getTitle();
        this.published = comment.getPublished();
        this.publishedAt = comment.getPublishedAt();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
        this.content = comment.getContent();
    }
}
