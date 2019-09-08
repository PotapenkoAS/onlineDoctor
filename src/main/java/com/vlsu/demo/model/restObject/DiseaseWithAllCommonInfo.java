package com.vlsu.demo.model.restObject;

import com.vlsu.demo.model.entity.Medicament;
import com.vlsu.demo.model.entity.Symptom;

import java.util.List;

public class DiseaseWithAllCommonInfo {
    private int diseaseId;
    private String diseaseName;
    private String diseaseInfo;
    private List<Medicament> medicamentList;
    private List<Symptom> symptomList;

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseInfo() {
        return diseaseInfo;
    }

    public void setDiseaseInfo(String diseaseInfo) {
        this.diseaseInfo = diseaseInfo;
    }

    public List<Medicament> getMedicamentList() {
        return medicamentList;
    }

    public void setMedicamentList(List<Medicament> medicamentList) {
        this.medicamentList = medicamentList;
    }

    public List<Symptom> getSymptomList() {
        return symptomList;
    }

    public void setSymptomList(List<Symptom> symptomList) {
        this.symptomList = symptomList;
    }

    public DiseaseWithAllCommonInfo(int diseaseId, String diseaseName, String diseaseInfo, List<Medicament> medicamentList, List<Symptom> symptomList) {
        this.diseaseId = diseaseId;
        this.diseaseName = diseaseName;
        this.diseaseInfo = diseaseInfo;
        this.medicamentList = medicamentList;
        this.symptomList = symptomList;
    }
}