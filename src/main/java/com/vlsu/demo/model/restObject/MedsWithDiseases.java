package com.vlsu.demo.model.restObject;

import com.vlsu.demo.model.entity.Disease;

import java.util.List;

public class MedsWithDiseases {
   private int medicamentId;
   private String medicamentName;
   private String medicamentInfo;
   private List<Disease> diseaseList;

    public int getMedicamentId() {
        return medicamentId;
    }

    public void setMedicamentId(int medicamentId) {
        this.medicamentId = medicamentId;
    }

    public String getMedicamentName() {
        return medicamentName;
    }

    public void setMedicamentName(String medicamentName) {
        this.medicamentName = medicamentName;
    }

    public String getMedicamentInfo() {
        return medicamentInfo;
    }

    public void setMedicamentInfo(String medicamentInfo) {
        this.medicamentInfo = medicamentInfo;
    }

    public List<Disease> getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(List<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }

    public MedsWithDiseases() {
    }

    public MedsWithDiseases(int medicamentId, String medicamentName, String medicamentInfo, List<Disease> diseaseList) {
        this.medicamentId = medicamentId;
        this.medicamentName = medicamentName;
        this.medicamentInfo = medicamentInfo;
        this.diseaseList = diseaseList;
    }
}
