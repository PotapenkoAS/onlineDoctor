package com.vlsu.demo.model.repository;

import com.vlsu.demo.model.entity.Disease;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface DiseaseRepository extends CrudRepository<Disease,Integer> {
    List<Disease> findAll();

    Disease findByDiseaseId(int id);
}
