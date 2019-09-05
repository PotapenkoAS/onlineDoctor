package com.vlsu.demo.controller;

import com.vlsu.demo.model.entity.Client;
import com.vlsu.demo.service.ClientService;
import com.vlsu.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {

    private ClientService clientService;
    private UserService userService;

    @Autowired
    public MyPageController(ClientService clientService, UserService userService) {
        this.clientService = clientService;
        this.userService = userService;
    }



    @GetMapping("/my_page")
    public String getMyPage(Model model) {
        model.addAttribute("client",clientService.getClientByUserId(userService.getCurrentUserId()));
        return "myPage/myPage";
    }
}
