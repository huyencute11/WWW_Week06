package com.example.www_week06.Service.Implement;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.www_week06.Service.Interface.IUserService;
import com.example.www_week06.models.User;
import com.example.www_week06.models.UserModel.RegisterModel;
import com.example.www_week06.models.UserModel.RegisterResultModel;
import com.example.www_week06.models.UserModel.TokenModel;
import com.example.www_week06.models.UserModel.UserInforModel;
import com.example.www_week06.models.UserModel.UserRequestDataModel;
import com.example.www_week06.models.UserModel.UserUpdateModel;
import com.example.www_week06.repositories.UserRepository;

@Service
public class UserService implements IUserService {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public TokenModel Login(String username, String password) {
        User user = userRepository.Login(username, HashPassword(password));

        if (user != null) {
            String token = createToken(25);
            user.setToken(token);
            LocalDateTime now = LocalDateTime.now();
            Date dt = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(dt);
            c.add(Calendar.DATE, 1);
            user.setExperiedDate(LocalDate.now());
            user.setLastLogin(LocalDate.now());
            userRepository.save(user);
            return new TokenModel(token, user.getFirstName() + user.getLastName(), true);
        } else {
            return new TokenModel("", "", false);
        }
    }

    public RegisterResultModel Register(RegisterModel model) {

        try {
            User user = new User();
            user.setFirstName(model.getFname());
            user.setMiddleName(model.getMname());
            user.setLastName(model.getLname());
            user.setEmail(model.getEmail());
            user.setMobile(model.getMobile());
            user.setPasswordHash(HashPassword(model.getPassword()));
            user.setRegisteredAt(LocalDate.now());
            userRepository.save(user);
            return new RegisterResultModel(user.getEmail(), true);
        } catch (Exception e) {
            return new RegisterResultModel(null, false);
        }
    }
    
    public UserInforModel GetUserProfile(UserRequestDataModel model) {
        User user = userRepository.GetUserDetails(model.getId());
        if (user == null) {
            return null;
        }
        return new UserInforModel(user, model.getToken().equals(user.getToken()));
    }
    
    private String HashPassword(String password) {
        String encryptedpassword = null;
        try {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptedpassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedpassword;
    }
    
    public String createToken(int n) 
    {
        // choose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb 
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
    public boolean CheckToken(String token) {
        User user = userRepository.ParseToken(token, LocalDate.now());
        if (user != null)
            return true;
        return false;
        
    }

    public boolean UpdateUser(UserUpdateModel updateModel) {
        User user = userRepository.ParseToken(updateModel.getToken(), LocalDate.now());
        user.setIntro(updateModel.getIntro());
        user.setProfile(updateModel.getProfile());
        User user2 = userRepository.save(user);
        if (user2 != null)
            return true;
        return false;

    }
}
