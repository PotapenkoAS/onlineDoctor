package com.vlsu.demo.controller;


import com.vlsu.demo.model.entity.Client;
import com.vlsu.demo.model.entity.User;
import com.vlsu.demo.service.LoginService;
import com.vlsu.demo.service.RegistrationService;
import com.vlsu.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class RegistrationController {

    private RegistrationService regService;
    private LoginService loginService;
    private UserService userService;

    @Autowired
    public RegistrationController(RegistrationService regService, LoginService loginService, UserService userService) {
        this.regService = regService;
        this.loginService = loginService;
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "login/registration";
    }

    @PostMapping("/registration")
    public String postRegistrationPage(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            return "login/registration";
        }
        String error;
        if ((error = userService.userExistsByLoginValidation(user.getLogin())) != null) {
            model.addAttribute("error", error);
            return "login/registration";
        }
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:/post_registration";
    }

    @GetMapping("/post_registration")
    public String getPostRegistrationPage(User user, Model model) {
        model.addAttribute("user", user);
        return "login/post_registration";
    }

    @PostMapping("/post_registration")
    public String postPostRegistrationPage(Client client, String password, String login, Model model) {
        User user = new User(login, password, "CLIENT");
        ArrayList<String> errorList = regService.saveClient(client, user);
        if (errorList.isEmpty()) {
            loginService.login(user);
            return "redirect:/test";
        }
        model.addAttribute("errorList", errorList);
        model.addAttribute("user", new User(login, password, "CLIENT"));
        return "login/post_registration";
    }
}
