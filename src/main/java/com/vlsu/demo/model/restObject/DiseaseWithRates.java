package com.vlsu.demo.model.restObject;

public class DiseaseWithRates {
    private int diseaseId;
    private String diseaseName;
    private String diseaseInfo;
    private Double mandatoryRate;
    private Double optionalRate;

    public DiseaseWithRates(int diseaseId, String diseaseName, String diseaseInfo, Double mandatoryRate, Double optionalRate) {
        this.diseaseId = diseaseId;
        this.diseaseName = diseaseName;
        this.diseaseInfo = diseaseInfo;
        this.mandatoryRate = mandatoryRate;
        this.optionalRate = optionalRate;
    }

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

    public Double getMandatoryRate() {
        return mandatoryRate;
    }

    public void setMandatoryRate(Double mandatoryRate) {
        this.mandatoryRate = mandatoryRate;
    }

    public Double getOptionalRate() {
        return optionalRate;
    }

    public void setOptionalRate(Double optionalRate) {
        this.optionalRate = optionalRate;
    }

}