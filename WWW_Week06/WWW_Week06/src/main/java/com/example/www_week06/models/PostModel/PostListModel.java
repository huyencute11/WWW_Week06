package com.example.www_week06.models.PostModel;

import com.example.www_week06.models.Post;

import lombok.Data;

@Data
public class PostListModel {
    private long id;
    private String title;
    private String metaTitle;
    private String summary;

    public PostListModel(Post post) {
        id = post.getId();
        title = post.getTitle();
        metaTitle = post.getMetaTitle();
        summary = post.getSummary();
    }
}
