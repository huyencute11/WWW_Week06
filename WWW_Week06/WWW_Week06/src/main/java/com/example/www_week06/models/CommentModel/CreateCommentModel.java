package com.example.www_week06.models.CommentModel;

import java.time.LocalDate;

import com.example.www_week06.models.Post;
import com.example.www_week06.models.PostComment;
import com.example.www_week06.models.User;

import lombok.Data;

@Data
public class CreateCommentModel {
    private Long postId;
    private String title;
    private int published;
    private LocalDate publishedAt;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String content;
    private String token;

    public PostComment ParseToEntity(User user, Post post, LocalDate now) {
        PostComment comment = new PostComment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setCreatedAt(now);
        comment.setUpdatedAt(now);
        comment.setPublishedAt(publishedAt);
        comment.setContent(content);
        comment.setTitle(title);
        comment.setPublished(published);

        return comment;
    }
}
