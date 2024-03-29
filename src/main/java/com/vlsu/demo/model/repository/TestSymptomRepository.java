package com.vlsu.demo.model.repository;

import com.vlsu.demo.model.entity.TestSymptom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestSymptomRepository extends CrudRepository<TestSymptom, Integer> {

}
