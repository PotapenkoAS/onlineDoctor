package com.vlsu.demo.controller;

import com.vlsu.demo.service.SymptomService;
import com.vlsu.demo.service.TestService;
import com.vlsu.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    private SymptomService symptomService;
    private TestService testService;
    private UserService userService;

    @Autowired
    public TestController(SymptomService symptomService, TestService testService, UserService userService) {
        this.symptomService = symptomService;
        this.testService = testService;
        this.userService = userService;
    }

    @GetMapping("/test")
    public String getTest(Model model) {
        model.addAttribute("list",symptomService.getAll());
        return "test";
    }

    @GetMapping("/tests")
    public String getTests(Model model){
        model.addAttribute("tests",testService.getAllByUserId(userService.getCurrentUserId()));
        return "tests";
    }
}
