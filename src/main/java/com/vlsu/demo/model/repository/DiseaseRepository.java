package com.vlsu.demo.model.repository;

import com.vlsu.demo.model.entity.Disease;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRepository extends CrudRepository<Disease,Integer> {
    List<Disease> findAll();

    Disease findByDiseaseId(int id);
}
