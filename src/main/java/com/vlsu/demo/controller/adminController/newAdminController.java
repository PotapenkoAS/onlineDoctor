package com.vlsu.demo.controller.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class newAdminController {

    @GetMapping("new_admin")
    public String getNewAdmin(){
        return "login/registration";
    }
}
