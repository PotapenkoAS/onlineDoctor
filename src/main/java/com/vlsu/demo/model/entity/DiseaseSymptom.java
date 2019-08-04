package com.vlsu.demo.model.entity;

import com.vlsu.demo.model.compositeKey.DiseaseSymptomKey;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(DiseaseSymptomKey.class)
@Table(name = "disease_symptom", schema = "online_doctor")
public class DiseaseSymptom {
    private Long diseaseId;
    private Long symptomId;
    private Disease diseaseByDiseaseId;
    private Symptom symptomBySymptomId;
    private Long rate;

    @Basic
    @Id
    @Column(name = "disease_id")
    public Long getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Long diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Basic
    @Id
    @Column(name = "symptom_id")
    public Long getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(Long symptomId) {
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
        return diseaseId.equals(that.diseaseId) &&
                symptomId.equals(that.symptomId);
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
