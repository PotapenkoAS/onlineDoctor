package com.vlsu.demo.service;

import com.vlsu.demo.model.entity.Client;
import com.vlsu.demo.model.entity.User;
import com.vlsu.demo.model.repository.ClientRepository;
import com.vlsu.demo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class RegistrationService {
    private UserRepository ur;
    private ClientRepository cr;
    private UserService userService;

    @Autowired
    public RegistrationService(UserRepository ur, ClientRepository cr, UserService userService) {
        this.ur = ur;
        this.cr = cr;
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
}