package com.vlsu.demo.service;

import com.vlsu.demo.model.CustomUserDetails;
import com.vlsu.demo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    private UserRepository ur;

    @Autowired
    public UserService(UserRepository ur) {
        this.ur = ur;
    }

    ArrayList<String> loginPasswordValidation(String login, String password) {
        int loginMinLength = 6;
        int passMinLength = 6;

        ArrayList<String> errorList = new ArrayList<>();
        if (login.length() < loginMinLength) {
            errorList.add("Логин слишком короткий");
        }
        if (password.length() < passMinLength) {
            errorList.add("Пароль слишком короткий");
        }
        return errorList;
    }

    public int getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getUserId();
        }else{
            return 0;
        }
    }

    public String userExistsByLoginValidation(String login) {
        if (ur.existsByLogin(login)) {
            return "Пользователь с таким именем уже существует";
        }
        return null;
    }

}
