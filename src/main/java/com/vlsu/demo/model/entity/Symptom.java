package com.vlsu.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Symptom {
    private int symptomId;
    private String name;
    private String info;
    private Collection<DiseaseSymptom> diseaseSymptomsBySymptomId;
    private Collection<TestSymptom> testSymptomsBySymptomId;

    @Id
    @Column(name = "symptom_id", nullable = false)
    public int getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(int symptomId) {
        this.symptomId = symptomId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "info", length = -1)
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

        if (symptomId != symptom.symptomId) return false;
        if (!Objects.equals(name, symptom.name)) return false;
        return Objects.equals(info, symptom.info);
    }

    @Override
    public int hashCode() {
        int result = symptomId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "symptomBySymptomId")
    public Collection<DiseaseSymptom> getDiseaseSymptomsBySymptomId() {
        return diseaseSymptomsBySymptomId;
    }

    public void setDiseaseSymptomsBySymptomId(Collection<DiseaseSymptom> diseaseSymptomsBySymptomId) {
        this.diseaseSymptomsBySymptomId = diseaseSymptomsBySymptomId;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "symptomBySymptomId")
    public Collection<TestSymptom> getTestSymptomsBySymptomId() {
        return testSymptomsBySymptomId;
    }

    public void setTestSymptomsBySymptomId(Collection<TestSymptom> testSymptomsBySymptomId) {
        this.testSymptomsBySymptomId = testSymptomsBySymptomId;
    }
}
