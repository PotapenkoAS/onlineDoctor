package com.vlsu.demo.controller.restController;

import com.vlsu.demo.model.entity.Medicament;
import com.vlsu.demo.model.entity.Symptom;
import com.vlsu.demo.model.restObject.SymptomWithRate;
import com.vlsu.demo.service.MedicamentService;
import com.vlsu.demo.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/symptoms")
    public Collection<Symptom> getSymptoms() {
        return symptomService.getAll();
    }

    @GetMapping("/meds")
    public Collection<Medicament> getMedicaments() {
        return medicamentService.getAll();
    }

    @DeleteMapping("/med")
    public void deleteMedicament(int diseaseId, int medicamentId) {
        medicamentService.deleteMedicamentFromDisease(diseaseId, medicamentId);
    }

    @DeleteMapping("/symptom")
    public void deleteSymptom(int diseaseId, int symptomId) {
        symptomService.deleteSymptomFromDisease(diseaseId, symptomId);
    }

    @PostMapping("/symptom")
    public SymptomWithRate saveSymptom(int symptomId, double rate){
        return null;
        //todo
    }
    //todo save medicament
}
