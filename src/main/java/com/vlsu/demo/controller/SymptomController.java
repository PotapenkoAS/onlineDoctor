package com.vlsu.demo.controller;

import com.vlsu.demo.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SymptomController {

    private SymptomService symptomService;

    @Autowired
    public SymptomController(SymptomService symptomService) {
        this.symptomService = symptomService;
    }

    @GetMapping("/symptoms")
    public String getSymptoms(Model model){
        model.addAttribute("symptomsList",symptomService.getAllCommonInfo());
        return "catalog/symptom_catalog";
    }
}
