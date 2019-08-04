package com.vlsu.demo.model.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Medicament {
    private int medicamentId;
    private String name;
    private String info;
    private Collection<DiseaseMed> diseaseMedsByMedicamentId;

    @Id
    @Column(name = "medicament_id", nullable = false)
    public int getMedicamentId() {
        return medicamentId;
    }

    public void setMedicamentId(int medicamentId) {
        this.medicamentId = medicamentId;
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

        Medicament that = (Medicament) o;

        if (medicamentId != that.medicamentId) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        int result = medicamentId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "medicamentByMedicamentId")
    public Collection<DiseaseMed> getDiseaseMedsByMedicamentId() {
        return diseaseMedsByMedicamentId;
    }

    public void setDiseaseMedsByMedicamentId(Collection<DiseaseMed> diseaseMedsByMedicamentId) {
        this.diseaseMedsByMedicamentId = diseaseMedsByMedicamentId;
    }
}
