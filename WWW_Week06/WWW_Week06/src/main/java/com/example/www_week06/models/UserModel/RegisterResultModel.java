package com.example.www_week06.models.UserModel;

import lombok.Data;

@Data
public class RegisterResultModel {
    private boolean IsSuccess;
    private String email;
    public RegisterResultModel(String email, boolean isSuccess) {
        this.email = email;
        this.IsSuccess = isSuccess;
    }
}
