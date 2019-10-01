package com.vlsu.demo.model.repository;

import com.vlsu.demo.model.compositeKey.DiseaseMedKey;
import com.vlsu.demo.model.entity.DiseaseMed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseMedicamentRepository extends CrudRepository<DiseaseMed, DiseaseMedKey> {
    List<DiseaseMed> findAll();
}
