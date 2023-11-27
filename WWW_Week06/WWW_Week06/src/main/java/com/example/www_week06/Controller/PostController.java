package com.example.www_week06.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.www_week06.Service.Interface.IPostService;
import com.example.www_week06.models.Post;
import com.example.www_week06.models.PostModel.PostCreateModel;
import com.example.www_week06.models.PostModel.PostListModel;
import com.example.www_week06.models.PostModel.PostModel;
import com.example.www_week06.models.UserModel.UserRequestDataModel;

@CrossOrigin("http://localhost:3000")
@RestController
public class PostController {

    @Autowired
    private IPostService postService;

    @PostMapping("/post/create")
    public ResponseEntity<String> CreatePost(@RequestBody PostCreateModel model) {
        boolean check = postService.CheckToken(model.getToken());
        if (!check)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Thất bại");
        Post post = postService.CreatePost(model);
        if (post != null)
            return ResponseEntity.ok("thành công");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("thất bại");
    }

    @PostMapping("/post/getAll")
    public ResponseEntity<Page<PostListModel>> getAllPosts(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "20") int size,
            @RequestBody UserRequestDataModel model) {
        boolean check = postService.CheckToken(model.getToken());
        if (!check)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        return ResponseEntity.ok(postService.GetAllPost(page, size));
    }
    
    @PostMapping("/post/getDetails")
    public ResponseEntity<PostModel> getPostDetails(@RequestBody UserRequestDataModel model) {
        boolean check = postService.CheckToken(model.getToken());
        if (!check)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

        PostModel post = postService.getPostDetails(model.getId());
        if (post == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.ok(post);
    }
}
