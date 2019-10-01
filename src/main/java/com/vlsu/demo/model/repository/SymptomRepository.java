package com.vlsu.demo.model.repository;

import com.vlsu.demo.model.entity.Symptom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SymptomRepository extends CrudRepository<Symptom, Integer> {
    List<Symptom> findAll();

    List<Symptom> findAllBySymptomIdIsIn(List<Integer> ids);

    Symptom findBySymptomId(int id);
}