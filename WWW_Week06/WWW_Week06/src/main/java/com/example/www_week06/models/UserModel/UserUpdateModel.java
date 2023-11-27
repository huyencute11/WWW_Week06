package com.example.www_week06.models.UserModel;

import com.example.www_week06.models.User;

import lombok.Data;

@Data
public class UserUpdateModel {
    private String intro;
    private String profile;
    private String token;

    public void UpdateEntity(User user) {
        user.setIntro(intro);
        user.setProfile(profile);
    }
}
