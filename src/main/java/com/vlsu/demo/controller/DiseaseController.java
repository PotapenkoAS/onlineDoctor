package com.vlsu.demo.controller;

import com.vlsu.demo.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiseaseController {

    DiseaseService diseaseService;

    @Autowired
    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping("diseases")
    public String getDiseases(Model model) {

        model.addAttribute("diseaseList",diseaseService.getAllCommonInfo());
        return "catalog/disease_catalog";
    }
}
