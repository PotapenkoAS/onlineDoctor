package com.vlsu.demo.model.repository;

import com.vlsu.demo.model.entity.Medicament;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface MedicamentRepository extends CrudRepository<Medicament,Integer> {
    Collection<Medicament> findAll();
}
