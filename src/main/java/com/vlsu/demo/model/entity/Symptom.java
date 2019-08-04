package com.vlsu.demo.model.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Symptom {
    private int symptomId;
    private String name;
    private String info;
    private Collection<DiseaseSymptom> diseaseSymptomsBySymptomId;

    @Id
    @Column(name = "symptom_id")
    public int getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(int symptomId) {
        this.symptomId = symptomId;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symptom symptom = (Symptom) o;
        return symptomId == (symptom.symptomId) &&
                Objects.equals(name, symptom.name) &&
                Objects.equals(info, symptom.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symptomId, name, info);
    }

    @OneToMany(mappedBy = "symptomBySymptomId")
    public Collection<DiseaseSymptom> getDiseaseSymptomsBySymptomId() {
        return diseaseSymptomsBySymptomId;
    }

    public void setDiseaseSymptomsBySymptomId(Collection<DiseaseSymptom> diseaseSymptomsBySymptomId) {
        this.diseaseSymptomsBySymptomId = diseaseSymptomsBySymptomId;
    }
}
