package com.example.www_week06.models.UserModel;

import java.util.List;
import java.util.stream.Collectors;

import com.example.www_week06.models.User;
import com.example.www_week06.models.PostModel.PostListModel;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserInforModel {
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobile;
    private String email;
    private String intro;
    private String profile;
    private List<PostListModel> posts;
    private boolean isMyAccount;

    public UserInforModel(User user, boolean isMyAccount) {
        firstName = user.getFirstName();
        middleName  = user.getMiddleName();
        lastName = user.getLastName();
        mobile = user.getMobile();
        intro = user.getIntro();
        profile = user.getProfile();
        email = user.getEmail();
        posts = user.getPosts().stream().map(x -> new PostListModel(x)).collect(Collectors.toList());
        this.isMyAccount = isMyAccount;
    }
}
