package com.vlsu.demo.model.restObject;

public class DiseaseWithRate {
    private int diseaseId;
    private String name;
    private String info;
    private Long mandatoryRate;
    private Long optionalRate;

    public DiseaseWithRate(int diseaseId, String name, String info, Long mandatoryRate, Long optionalRate) {
        this.diseaseId = diseaseId;
        this.name = name;
        this.info = info;
        this.mandatoryRate = mandatoryRate;
        this.optionalRate = optionalRate;
    }

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

    public Long getMandatoryRate() {
        return mandatoryRate;
    }

    public void setMandatoryRate(Long mandatoryRate) {
        this.mandatoryRate = mandatoryRate;
    }

    public Long getOptionalRate() {
        return optionalRate;
    }

    public void setOptionalRate(Long optionalRate) {
        this.optionalRate = optionalRate;
    }
}
