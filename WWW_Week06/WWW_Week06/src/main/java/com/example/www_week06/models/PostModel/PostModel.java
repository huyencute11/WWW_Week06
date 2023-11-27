package com.example.www_week06.models.PostModel;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.example.www_week06.models.Post;
import com.example.www_week06.models.CommentModel.CommentModel;

import lombok.Data;

@Data
public class PostModel {
    private long id;
    private String userName;
    private Long userId;
    private String title;
    private String metaTitle;
    private String summary;
    private int published;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate publishedAt;
    private String content;
    private List<CommentModel> comments;

    public PostModel(Post post) {
        id = post.getId();
        userName = post.getUser().getFirstName();
        userId = post.getUser().getId();
        title = post.getTitle();
        metaTitle = post.getMetaTitle();
        summary = post.getSummary();
        publishedAt = post.getPublishedAt();
        createdAt = post.getCreatedAt();
        updatedAt = post.getUpdatedAt();
        publishedAt = post.getPublishedAt();
        content = post.getContent();
        comments = post.getPostComments().stream().map(x -> new CommentModel(x)).collect(Collectors.toList());
    }
}
