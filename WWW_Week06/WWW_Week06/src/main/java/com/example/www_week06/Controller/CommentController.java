package com.example.www_week06.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.www_week06.Service.Interface.ICommentService;
import com.example.www_week06.models.PostComment;
import com.example.www_week06.models.CommentModel.CreateCommentModel;

@CrossOrigin("http://localhost:3000")
@RestController
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @PostMapping("/comment/create")
    public ResponseEntity<String> CreateComment(@RequestBody CreateCommentModel model) {
        boolean check = commentService.CheckToken(model.getToken());
        if (!check)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Thất bại");
        PostComment comment = commentService.createComment(model);
        if (comment != null)
            return ResponseEntity.ok("thành công");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("thất bại");
    }
}
