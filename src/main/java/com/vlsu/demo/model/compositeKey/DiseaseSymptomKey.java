package com.vlsu.demo.model.compositeKey;

import java.io.Serializable;

public class DiseaseSymptomKey implements Serializable {
    private int diseaseId;
    private int symptomId;

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public int getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(int symptomId) {
        this.symptomId = symptomId;
    }

    public DiseaseSymptomKey(int diseaseId, int symptomId) {
        this.diseaseId = diseaseId;
        this.symptomId = symptomId;
    }
}
