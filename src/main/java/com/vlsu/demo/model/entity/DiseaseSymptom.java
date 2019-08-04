package com.vlsu.demo.model.entity;

import com.vlsu.demo.model.compositeKey.DiseaseSymptomKey;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(DiseaseSymptomKey.class)
@Table(name = "disease_symptom", schema = "online_doctor")
public class DiseaseSymptom {
    private int diseaseId;
    private int symptomId;
    private Disease diseaseByDiseaseId;
    private Symptom symptomBySymptomId;
    private Long rate;

    @Basic
    @Id
    @Column(name = "disease_id")
    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Basic
    @Id
    @Column(name = "symptom_id")
    public int getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(int symptomId) {
        this.symptomId = symptomId;
    }

    @Basic
    @Column(name = "rate")
    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiseaseSymptom that = (DiseaseSymptom) o;
        return diseaseId == (that.diseaseId) &&
                symptomId == (that.symptomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diseaseId, symptomId);
    }

    @ManyToOne
    @JoinColumn(name = "disease_id", referencedColumnName = "disease_id", nullable = false, insertable = false, updatable = false)
    public Disease getDiseaseByDiseaseId() {
        return diseaseByDiseaseId;
    }

    public void setDiseaseByDiseaseId(Disease diseaseByDiseaseId) {
        this.diseaseByDiseaseId = diseaseByDiseaseId;
    }

    @ManyToOne
    @JoinColumn(name = "symptom_id", referencedColumnName = "symptom_id", nullable = false, insertable = false, updatable = false)
    public Symptom getSymptomBySymptomId() {
        return symptomBySymptomId;
    }

    public void setSymptomBySymptomId(Symptom symptomBySymptomId) {
        this.symptomBySymptomId = symptomBySymptomId;
    }


}
