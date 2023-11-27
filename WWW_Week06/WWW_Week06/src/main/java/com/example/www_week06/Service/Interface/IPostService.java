package com.example.www_week06.Service.Interface;

import org.springframework.data.domain.Page;

import com.example.www_week06.models.Post;
import com.example.www_week06.models.PostModel.PostCreateModel;
import com.example.www_week06.models.PostModel.PostListModel;
import com.example.www_week06.models.PostModel.PostModel;

public interface IPostService {

    Post CreatePost(PostCreateModel model);

    boolean CheckToken(String token);

    Page<PostListModel> GetAllPost(int page, int pageSize);

    PostModel getPostDetails(Long id);
}
