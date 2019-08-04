package com.vlsu.demo.model.entity;

import com.vlsu.demo.model.compositeKey.DiseaseMedKey;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(DiseaseMedKey.class)
@Table(name = "disease_med", schema = "online_doctor")
public class DiseaseMed {
    private int diseaseId;
    private int medicamentId;
    private Disease diseaseByDiseaseId;
    private Medicament medicamentByMedicamentId;
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
    @Column(name = "medicament_id")
    public int getMedicamentId() {
        return medicamentId;
    }

    public void setMedicamentId(int medicamentId) {
        this.medicamentId = medicamentId;
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
        DiseaseMed that = (DiseaseMed) o;
        return diseaseId == (that.diseaseId) &&
                Objects.equals(medicamentId, that.medicamentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diseaseId, medicamentId);
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
    @JoinColumn(name = "medicament_id", referencedColumnName = "medicament_id", insertable = false, updatable = false)
    public Medicament getMedicamentByMedicamentId() {
        return medicamentByMedicamentId;
    }

    public void setMedicamentByMedicamentId(Medicament medicamentByMedicamentId) {
        this.medicamentByMedicamentId = medicamentByMedicamentId;
    }


}
