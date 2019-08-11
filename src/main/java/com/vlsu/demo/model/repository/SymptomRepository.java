package com.vlsu.demo.model.repository;

import com.vlsu.demo.model.entity.Symptom;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface SymptomRepository extends CrudRepository<Symptom,Integer> {
    Collection<Symptom> findAll();
}
