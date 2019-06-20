package com.vlsu.demo.model.compositeKey;

import java.io.Serializable;

public class DiseaseMedKey implements Serializable {
    private int diseaseId;
    private Integer medicamentId;

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public Integer getMedicamentId() {
        return medicamentId;
    }

    public void setMedicamentId(Integer medicamentId) {
        this.medicamentId = medicamentId;
    }
}
