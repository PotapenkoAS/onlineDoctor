package com.vlsu.demo.model.repository;

import com.vlsu.demo.model.entity.Disease;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface DiseaseRepository extends CrudRepository<Disease,Integer> {
    Collection<Disease> findAll();
}
