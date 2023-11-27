package com.example.www_week06.Service.Implement;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.www_week06.Service.Interface.ICommentService;
import com.example.www_week06.models.Post;
import com.example.www_week06.models.PostComment;
import com.example.www_week06.models.User;
import com.example.www_week06.models.CommentModel.CreateCommentModel;
import com.example.www_week06.repositories.CommentRepository;
import com.example.www_week06.repositories.PostRepository;
import com.example.www_week06.repositories.UserRepository;

@Service
public class CommentService implements ICommentService {
    
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public PostComment createComment(CreateCommentModel model) {
        User user = userRepository.ParseToken(model.getToken(), LocalDate.now());
        if (user == null) {
            return null;
        }
        Post post = postRepository.findById(model.getPostId()).orElse(null);
        if (post == null) {
            return null;
        }

        PostComment comment = commentRepository.save(model.ParseToEntity(user, post, LocalDate.now()));
        return comment;
    }

    public boolean CheckToken(String token) {
        User user = userRepository.ParseToken(token, LocalDate.now());
        if (user != null)
            return true;
        return false;
        
    }
}
