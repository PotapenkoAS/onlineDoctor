package com.vlsu.demo.model.restObject;

import java.util.List;

public class DiseaseWithAllInfo {
    private int diseaseId;
    private String name;
    private String info;
    private List<SymptomWithRate> symptomWithRateList;
    private List<MedicamentWithRate> medicamentWithRateList;

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<SymptomWithRate> getSymptomWithRateList() {
        return symptomWithRateList;
    }

    public void setSymptomWithRateList(List<SymptomWithRate> symptomWithRateList) {
        this.symptomWithRateList = symptomWithRateList;
    }

    public List<MedicamentWithRate> getMedicamentWithRateList() {
        return medicamentWithRateList;
    }

    public void setMedicamentWithRateList(List<MedicamentWithRate> medicamentWithRateList) {
        this.medicamentWithRateList = medicamentWithRateList;
    }

    public DiseaseWithAllInfo() {
    }

    public DiseaseWithAllInfo(int diseaseId, String name, String info, List<SymptomWithRate> symptomWithRateList, List<MedicamentWithRate> medicamentWithRateList) {
        this.diseaseId = diseaseId;
        this.name = name;
        this.info = info;
        this.symptomWithRateList = symptomWithRateList;
        this.medicamentWithRateList = medicamentWithRateList;
    }
}
