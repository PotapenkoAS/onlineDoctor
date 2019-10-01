package com.vlsu.demo.controller.restController;

import com.vlsu.demo.model.entity.DiseaseMed;
import com.vlsu.demo.model.entity.DiseaseSymptom;
import com.vlsu.demo.model.entity.Medicament;
import com.vlsu.demo.model.entity.Symptom;
import com.vlsu.demo.model.restObject.MedicamentWithRate;
import com.vlsu.demo.model.restObject.SymptomWithRate;
import com.vlsu.demo.service.DiseaseMedicamentService;
import com.vlsu.demo.service.DiseaseSymptomService;
import com.vlsu.demo.service.MedicamentService;
import com.vlsu.demo.service.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class CatalogRestController {

    private SymptomService symptomService;
    private DiseaseSymptomService diseaseSymptomService;
    private MedicamentService medicamentService;
    private DiseaseMedicamentService diseaseMedicamentService;

    @Autowired
    public CatalogRestController(SymptomService symptomService, DiseaseSymptomService diseaseSymptomService, MedicamentService medicamentService, DiseaseMedicamentService diseaseMedicamentService) {
        this.symptomService = symptomService;
        this.diseaseSymptomService = diseaseSymptomService;
        this.medicamentService = medicamentService;
        this.diseaseMedicamentService = diseaseMedicamentService;
    }


    @GetMapping("/symptoms")
    public List<Symptom> getSymptoms() {
        return symptomService.getAll();
    }

    @GetMapping("/meds")
    public List<Medicament> getMedicaments() {
        return medicamentService.getAll();
    }

    @DeleteMapping("/med")
    public void deleteMedicament(int diseaseId, int medicamentId) {
        medicamentService.deleteMedicamentFromDisease(diseaseId, medicamentId);
    }

    @DeleteMapping("/symptom")
    public void deleteSymptom(int diseaseId, int symptomId) {
        diseaseSymptomService.deleteSymptomFromDisease(diseaseId, symptomId);
    }

    @PostMapping("/symptom")
    public SymptomWithRate saveSymptom(int symptomId, int diseaseId, double rate, byte mandatory) {
        DiseaseSymptom ds = diseaseSymptomService.saveDiseaseSymptom(symptomId, diseaseId, rate, mandatory);
        Symptom symptom = symptomService.getById(symptomId);
        return new SymptomWithRate(ds.getSymptomId(), symptom.getName(), symptom.getInfo(), ds.getRate(), ds.getMandatory(), ds.getDiseaseId());
    }

    @PostMapping("/medicament")
    public MedicamentWithRate saveMedicament(int medicamentId, int diseaseId, double rate){
        DiseaseMed dm = diseaseMedicamentService.saveDiseaseMedicament(medicamentId,diseaseId,rate);
        Medicament medicament = medicamentService.getById(medicamentId);
        return new MedicamentWithRate(medicament.getMedicamentId(),medicament.getName(),medicament.getInfo(),dm.getRate(),dm.getDiseaseId());
    }
}
