package com.example.www_week06.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.www_week06.Service.Interface.IUserService;
import com.example.www_week06.models.UserModel.LoginModel;
import com.example.www_week06.models.UserModel.RegisterModel;
import com.example.www_week06.models.UserModel.RegisterResultModel;
import com.example.www_week06.models.UserModel.TokenModel;
import com.example.www_week06.models.UserModel.UserInforModel;
import com.example.www_week06.models.UserModel.UserRequestDataModel;
import com.example.www_week06.models.UserModel.UserUpdateModel;
@CrossOrigin("http://localhost:3000")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenModel> Login(@RequestBody LoginModel model) {
        TokenModel tokenModel = userService.Login(model.getUsername(), model.getPassword());
        if (tokenModel.isIsSuccess()) {
            return ResponseEntity.ok(tokenModel);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tokenModel);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResultModel> Register(@RequestBody RegisterModel model) {
        RegisterResultModel result = userService.Register(model);
        if (result.isIsSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
    @PostMapping("/update")
    public ResponseEntity<String> Update(@RequestBody UserUpdateModel model) {
        boolean check = userService.CheckToken(model.getToken());
        if (!check)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("thất bại");
        boolean updateStatus = userService.UpdateUser(model);
        if (updateStatus)
            return ResponseEntity.ok("update ok");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("thất bại");
    }

    @PostMapping("/getDetails")
    public ResponseEntity<UserInforModel> GetUserDetails(@RequestBody UserRequestDataModel model) {
        boolean check = userService.CheckToken(model.getToken());
        if (!check)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        UserInforModel user = userService.GetUserProfile(model);
        if (user != null)
            return ResponseEntity.ok(user);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    
}
