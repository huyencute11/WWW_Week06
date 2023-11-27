package com.example.www_week06.Service.Implement;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.www_week06.Service.Interface.IPostService;
import com.example.www_week06.models.Post;
import com.example.www_week06.models.User;
import com.example.www_week06.models.PostModel.PostCreateModel;
import com.example.www_week06.models.PostModel.PostListModel;
import com.example.www_week06.models.PostModel.PostModel;
import com.example.www_week06.repositories.PostRepository;
import com.example.www_week06.repositories.UserRepository;

@Service
public class PostService implements IPostService {
    
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public Post CreatePost(PostCreateModel model) {
        Post postParent = null;
        if (model.getParentId() != null) {
            postParent = postRepository.findById(model.getParentId()).orElse(null);
            if (postParent == null) {
                return null;
            }
        }

        User user = userRepository.ParseToken(model.getToken(), LocalDate.now());
        Post post = model.ParseToEntity(postParent, user, LocalDate.now());

        post = postRepository.save(post);
        return post;
    }

    public Page<PostListModel> GetAllPost(int page, int pageSize) {
        return postRepository.findAllAsDto(PageRequest.of(page, pageSize));
    }

    public PostModel getPostDetails(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return null;
        }
        return new PostModel(post);
    }

    public boolean CheckToken(String token) {
        User user = userRepository.ParseToken(token, LocalDate.now());
        if (user != null)
            return true;
        return false;
        
    }
}
