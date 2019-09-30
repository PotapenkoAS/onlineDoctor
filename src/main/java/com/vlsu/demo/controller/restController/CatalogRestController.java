package com.vlsu.demo.controller.restController;

import com.vlsu.demo.model.entity.Medicament;
import com.vlsu.demo.model.entity.Symptom;
import com.vlsu.demo.service.MedicamentService;
import com.vlsu.demo.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/rest")
public class CatalogRestController {

    private SymptomService symptomService;
    private MedicamentService medicamentService;

    @Autowired
    public CatalogRestController(SymptomService symptomService, MedicamentService medicamentService) {
        this.symptomService = symptomService;
        this.medicamentService = medicamentService;
    }

    @GetMapping("/get_symptoms")
    public Collection<Symptom> getSymptoms() {
        return symptomService.getAll();
    }

    @GetMapping("/get_meds")
    public Collection<Medicament> getMedicaments() {
        return medicamentService.getAll();
    }

    //TODO delete methods for symptoms and meds
}
