package com.vlsu.demo.model.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Disease {
    private int diseaseId;
    private String name;
    private String info;
    private Collection<DiseaseMed> diseaseMedsByDiseaseId;
    private Collection<DiseaseSymptom> diseaseSymptomsByDiseaseId;

    @Id
    @Column(name = "disease_id", nullable = false)
    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
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

        Disease disease = (Disease) o;

        if (diseaseId != disease.diseaseId) return false;
        if (!Objects.equals(name, disease.name)) return false;
        return Objects.equals(info, disease.info);
    }

    @Override
    public int hashCode() {
        int result = diseaseId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
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
