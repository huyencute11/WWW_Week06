package com.example.www_week06.Service.Interface;

import com.example.www_week06.models.UserModel.RegisterModel;
import com.example.www_week06.models.UserModel.RegisterResultModel;
import com.example.www_week06.models.UserModel.TokenModel;
import com.example.www_week06.models.UserModel.UserInforModel;
import com.example.www_week06.models.UserModel.UserRequestDataModel;
import com.example.www_week06.models.UserModel.UserUpdateModel;

public interface IUserService {
    TokenModel Login(String username, String password);

    RegisterResultModel Register(RegisterModel model);

    boolean UpdateUser(UserUpdateModel updateModel);

    boolean CheckToken(String token);
    
    UserInforModel GetUserProfile(UserRequestDataModel model);
}
