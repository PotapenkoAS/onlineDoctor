package com.vlsu.demo.model.repository;

import com.vlsu.demo.model.entity.Symptom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomRepository extends CrudRepository<Symptom, Integer> {
    List<Symptom> findAll();

    List<Symptom> findAllBySymptomIdIsIn(List<Integer> ids);

    Symptom findBySymptomId(int id);
}