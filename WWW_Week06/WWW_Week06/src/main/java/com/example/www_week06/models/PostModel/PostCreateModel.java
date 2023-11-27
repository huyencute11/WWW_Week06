package com.example.www_week06.models.PostModel;

import java.time.LocalDate;

import com.example.www_week06.models.Post;
import com.example.www_week06.models.User;

import lombok.Data;

@Data
public class PostCreateModel {
    private String title;
    private String metaTitle;
    private String content;
    private Long parentId;
    private String summary;
    private int published;
    private String token;

    public Post ParseToEntity(Post parentPost, User user, LocalDate now) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setMetaTitle(metaTitle);
        post.setParent(parentPost);
        post.setSummary(summary);
        post.setPublished(published);

        post.setUpdatedAt(now);
        post.setCreatedAt(now);
        post.setUser(user);

        return post;
    }
}
