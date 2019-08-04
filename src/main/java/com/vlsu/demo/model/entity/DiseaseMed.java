package com.vlsu.demo.model.entity;

import com.vlsu.demo.model.compositeKey.DiseaseMedKey;

import javax.persistence.*;

@Entity
@IdClass(DiseaseMedKey.class)
@Table(name = "disease_med", schema = "online_doctor")
public class DiseaseMed {
    private int diseaseId;
    private int medicamentId;
    private double rate;
    private Disease diseaseByDiseaseId;
    private Medicament medicamentByMedicamentId;

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
    @Id
    @Column(name = "medicament_id", nullable = false)
    public int getMedicamentId() {
        return medicamentId;
    }

    public void setMedicamentId(int medicamentId) {
        this.medicamentId = medicamentId;
    }

    @Basic
    @Column(name = "rate", nullable = false, precision = 2)
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiseaseMed that = (DiseaseMed) o;

        if (diseaseId != that.diseaseId) return false;
        if (medicamentId != that.medicamentId) return false;
        return Double.compare(that.rate, rate) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = diseaseId;
        result = 31 * result + medicamentId;
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
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
    @JoinColumn(name = "medicament_id", referencedColumnName = "medicament_id", nullable = false, insertable = false, updatable = false)
    public Medicament getMedicamentByMedicamentId() {
        return medicamentByMedicamentId;
    }

    public void setMedicamentByMedicamentId(Medicament medicamentByMedicamentId) {
        this.medicamentByMedicamentId = medicamentByMedicamentId;
    }
}
