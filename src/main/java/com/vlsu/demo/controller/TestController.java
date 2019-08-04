package com.vlsu.demo.controller;

import com.vlsu.demo.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    private SymptomService symptomService;

    @Autowired
    public TestController(SymptomService symptomService) {
        this.symptomService = symptomService;
    }

    @GetMapping("/test")
    public String getTest(Model model) {
        model.addAttribute("list",symptomService.getAll());
        return "test";
    }
}
