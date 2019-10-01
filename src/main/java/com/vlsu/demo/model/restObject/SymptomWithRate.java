package com.vlsu.demo.model.restObject;

public class SymptomWithRate {
    private int symptomId;
    private String name;
    private String info;
    private double rate;
    private byte mandatory;
    private int diseaseId;


    public int getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(int symptomId) {
        this.symptomId = symptomId;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public byte isMandatory() {
        return mandatory;
    }

    public void setMandatory(byte mandatory) {
        this.mandatory = mandatory;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public SymptomWithRate() {
    }

    public SymptomWithRate(int symptomId, String name, String info, double rate, byte mandatory, int diseaseId) {
        this.symptomId = symptomId;
        this.name = name;
        this.info = info;
        this.rate = rate;
        this.mandatory = mandatory;
        this.diseaseId = diseaseId;
    }
}
