package com.vlsu.demo.service;

import com.vlsu.demo.model.entity.Admin;
import com.vlsu.demo.model.entity.Client;
import com.vlsu.demo.model.entity.User;
import com.vlsu.demo.model.repository.AdminRepository;
import com.vlsu.demo.model.repository.ClientRepository;
import com.vlsu.demo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class RegistrationService {
    private UserRepository ur;
    private ClientRepository cr;
    private AdminRepository ar;
    private UserService userService;

    @Autowired
    public RegistrationService(UserRepository ur, ClientRepository cr, AdminRepository ar, UserService userService) {
        this.ur = ur;
        this.cr = cr;
        this.ar = ar;
        this.userService = userService;
    }

    // аннотация для нормальной работы сохранения
    @Transactional
    public ArrayList<String> saveClient(Client client, User user) {
        ArrayList<String> errorList = userService.loginPasswordValidation(user.getLogin(), user.getPassword());
        if (errorList.isEmpty()) {
            if (!ur.existsByLoginIs(user.getLogin())) {
                user = ur.save(user);
            } else {
                errorList.add("Пользователь с таким именем уже существует");
                return errorList;
            }
        } else {
            return errorList;
        }
        client.setUserId(user.getUserId());
        cr.save(client);
        return errorList;
    }

    @Transactional
    public ArrayList<String> saveAdmin(Admin admin, User user) {
        ArrayList<String> errorList = userService.loginPasswordValidation(user.getLogin(), user.getPassword());
        if (errorList.isEmpty()) {
            if (!ur.existsByLoginIs(user.getLogin())) {
                user = ur.save(user);
            } else {
                errorList.add("Пользователь с таким именем уже существует");
                return errorList;
            }
        } else {
            return errorList;
        }
        admin.setUserId(user.getUserId());
        ar.save(admin);
        return errorList;
    }

}