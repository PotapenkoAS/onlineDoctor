package com.vlsu.demo.controller.restController;

import com.vlsu.demo.model.repository.ClientRepository;
import com.vlsu.demo.model.restObject.DiseaseWithMeds;
import com.vlsu.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/rest")
public class TestRestController {

    private DiseaseService diseaseService;
    private UserService userService;
    private TestService testService;
    private RestService restService;

    @Autowired
    public TestRestController(DiseaseService diseaseService, UserService userService, TestService testService, RestService restService) {
        this.diseaseService = diseaseService;
        this.userService = userService;
        this.testService = testService;
        this.restService = restService;
    }

    @GetMapping("/update_diseases")
    public ResponseEntity updateDiseases(@RequestParam(name = "symptoms") String symptoms) {
        if (symptoms.equals("")) {
            return ResponseEntity.status(204).body(null);
        }
        return ResponseEntity.ok(diseaseService.getAllBySymptoms(symptoms));
    }

    @PostMapping("/save_test")
    public String saveTest(String symptoms) {
        String parsedSymptoms = restService.convertArrayStringToString(symptoms);
        if (parsedSymptoms.equals("")) {
            return "empty";
        } else if (testService.saveTest(userService.getCurrentUserId(), parsedSymptoms)) {
            return "success";
        } else {
            return "error";
        }
    }

}
