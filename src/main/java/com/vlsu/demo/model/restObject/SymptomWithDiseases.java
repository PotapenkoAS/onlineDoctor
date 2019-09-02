package com.vlsu.demo.model.restObject;

import com.vlsu.demo.model.entity.Disease;

import java.util.List;

public class SymptomWithDiseases {
    private int symptomId;
    private String symptomName;
    private String symptomInfo;
    private List<Disease> diseaseList;

    public SymptomWithDiseases() {

    }

    public int getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(int symptomId) {
        this.symptomId = symptomId;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }

    public String getSymptomInfo() {
        return symptomInfo;
    }

    public void setSymptomInfo(String symptomInfo) {
        this.symptomInfo = symptomInfo;
    }

    public List<Disease> getDiseaseList() {
        return diseaseList;
    }

    public void setDiseaseList(List<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }

    public SymptomWithDiseases(int symptomId, String symptomName, String symptomInfo, List<Disease> diseaseList) {
        this.symptomId = symptomId;
        this.symptomName = symptomName;
        this.symptomInfo = symptomInfo;
        this.diseaseList = diseaseList;
    }
}
