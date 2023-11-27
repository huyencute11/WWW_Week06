package com.example.www_week06.Service.Interface;

import org.springframework.stereotype.Repository;

import com.example.www_week06.models.PostComment;
import com.example.www_week06.models.CommentModel.CreateCommentModel;

@Repository
public interface ICommentService {
    boolean CheckToken(String token);

    PostComment createComment(CreateCommentModel model);
}
