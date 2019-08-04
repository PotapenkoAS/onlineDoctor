package com.vlsu.demo.model.restObject;

public class DiseaseWithRate {
    private Long diseaseId;
    private String name;
    private String info;
    private Long rate;

    public DiseaseWithRate(Long diseaseId, String name, String info, Long rate) {
        this.diseaseId = diseaseId;
        this.name = name;
        this.info = info;
        this.rate = rate;
    }

    public Long getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Long diseaseId) {
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

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }
}
