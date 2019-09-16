package com.vlsu.demo.controller;

import com.vlsu.demo.service.MedicamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MedicamentController {

    private MedicamentService medicamentService;

    @Autowired
    public MedicamentController(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    @GetMapping("/medicaments")
    public String getMedicaments(Model model){
        model.addAttribute("meds",medicamentService.getAllCommonInfo());
        return "catalog/medicament_catalog";
    }
}
