package com.vlsu.demo.service;

import com.vlsu.demo.model.compositeKey.DiseaseSymptomKey;
import com.vlsu.demo.model.entity.DiseaseSymptom;
import com.vlsu.demo.model.repository.DiseaseSymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Service
public class DiseaseSymptomService {

    private DiseaseSymptomRepository dsr;

    @Autowired
    public DiseaseSymptomService(DiseaseSymptomRepository dsr) {
        this.dsr = dsr;
    }

    @Transactional
    public void deleteSymptomFromDisease(int diseaseId, int symptomId) {
        dsr.deleteById(new DiseaseSymptomKey(diseaseId,symptomId));
    }

    @Transactional
    public DiseaseSymptom saveDiseaseSymptom(int symptomId, int diseaseId, double rate,byte mandatory) {
        DiseaseSymptom ds = new DiseaseSymptom();
        ds.setDiseaseId(diseaseId);
        ds.setSymptomId(symptomId);
        ds.setRate(rate);
        ds.setMandatory(mandatory);
        return dsr.save(ds);
    }
}
