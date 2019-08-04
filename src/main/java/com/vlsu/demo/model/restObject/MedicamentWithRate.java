package com.vlsu.demo.model.restObject;

public class MedicamentWithRate {

    private int medicamentId;
    private String name;
    private String info;
    private double rate;
    private int diseaseId;

    public MedicamentWithRate(int medicamentId, String name, String info, double rate, int diseaseId) {
        this.medicamentId = medicamentId;
        this.name = name;
        this.info = info;
        this.rate = rate;
        this.diseaseId = diseaseId;
    }

    public int getMedicamentId() {
        return medicamentId;
    }

    public void setMedicamentId(int medicamentId) {
        this.medicamentId = medicamentId;
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

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }
}
