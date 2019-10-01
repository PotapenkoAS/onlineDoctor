package com.vlsu.demo.model.repository;

import com.vlsu.demo.model.entity.Medicament;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MedicamentRepository extends CrudRepository<Medicament, Integer> {
    List<Medicament> findAll();

    Medicament findByMedicamentId(int id);
}
