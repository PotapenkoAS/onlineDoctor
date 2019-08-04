package com.vlsu.demo.controller.restController;

import com.vlsu.demo.model.restObject.DiseaseWithRate;
import com.vlsu.demo.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/rest")
public class TestRestController {

    private DiseaseService diseaseService;

    @Autowired
    public TestRestController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping("/update_diseases")
    public ArrayList<DiseaseWithRate> updateDiseases(@RequestParam(name = "symptoms") String symptoms) {
        return diseaseService.getAllBySymptoms(symptoms);
    }
}
