package com.vlsu.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vlsu.demo.model.compositeKey.DiseaseSymptomKey;

import javax.persistence.*;

@Entity
@IdClass(DiseaseSymptomKey.class)
@Table(name = "disease_symptom", schema = "online_doctor")
public class DiseaseSymptom {
    private int symptomId;
    private int diseaseId;
    private double rate;
    private byte mandatory;
    private Symptom symptomBySymptomId;
    private Disease diseaseByDiseaseId;

    @Basic
    @Id
    @Column(name = "symptom_id", nullable = false)
    public int getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(int symptomId) {
        this.symptomId = symptomId;
    }

    @Basic
    @Id
    @Column(name = "disease_id", nullable = false)
    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Basic
    @Column(name = "rate", nullable = false, precision = 2)
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Basic
    @Column(name = "mandatory", nullable = false)
    public byte getMandatory() {
        return mandatory;
    }

    public void setMandatory(byte mandatory) {
        this.mandatory = mandatory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiseaseSymptom that = (DiseaseSymptom) o;

        if (symptomId != that.symptomId) return false;
        if (diseaseId != that.diseaseId) return false;
        if (Double.compare(that.rate, rate) != 0) return false;
        return mandatory == that.mandatory;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = symptomId;
        result = 31 * result + diseaseId;
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) mandatory;
        return result;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "symptom_id", referencedColumnName = "symptom_id", nullable = false, insertable = false, updatable = false)
    public Symptom getSymptomBySymptomId() {
        return symptomBySymptomId;
    }

    public void setSymptomBySymptomId(Symptom symptomBySymptomId) {
        this.symptomBySymptomId = symptomBySymptomId;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "disease_id", referencedColumnName = "disease_id", nullable = false, insertable = false, updatable = false)
    public Disease getDiseaseByDiseaseId() {
        return diseaseByDiseaseId;
    }

    public void setDiseaseByDiseaseId(Disease diseaseByDiseaseId) {
        this.diseaseByDiseaseId = diseaseByDiseaseId;
    }
}
