package com.example.www_week06.models.UserModel;

import lombok.Data;

@Data
public class TokenModel {
    private boolean IsSuccess;
    private String token;
    private String userName;
    public TokenModel(String token, String userName, boolean isSuccess) {
        this.token = token;
        this.userName = userName;
        this.IsSuccess = isSuccess;
    }
}
