package com.vlsu.demo.model.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Disease {
    private Long diseaseId;
    private String name;
    private String info;
    private Collection<DiseaseMed> diseaseMedsByDiseaseId;
    private Collection<DiseaseSymptom> diseaseSymptomsByDiseaseId;

    @Id
    @Column(name = "disease_id")
    public Long getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(Long diseaseId) {
        this.diseaseId = diseaseId;
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
        Disease disease = (Disease) o;
        return diseaseId.equals(disease.diseaseId) &&
                Objects.equals(name, disease.name) &&
                Objects.equals(info, disease.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diseaseId, name, info);
    }

    @OneToMany(mappedBy = "diseaseByDiseaseId")
    public Collection<DiseaseMed> getDiseaseMedsByDiseaseId() {
        return diseaseMedsByDiseaseId;
    }

    public void setDiseaseMedsByDiseaseId(Collection<DiseaseMed> diseaseMedsByDiseaseId) {
        this.diseaseMedsByDiseaseId = diseaseMedsByDiseaseId;
    }

    @OneToMany(mappedBy = "diseaseByDiseaseId")
    public Collection<DiseaseSymptom> getDiseaseSymptomsByDiseaseId() {
        return diseaseSymptomsByDiseaseId;
    }

    public void setDiseaseSymptomsByDiseaseId(Collection<DiseaseSymptom> diseaseSymptomsByDiseaseId) {
        this.diseaseSymptomsByDiseaseId = diseaseSymptomsByDiseaseId;
    }
}
