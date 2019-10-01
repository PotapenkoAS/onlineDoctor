package com.vlsu.demo.model.repository;

import com.vlsu.demo.model.compositeKey.DiseaseSymptomKey;
import com.vlsu.demo.model.entity.DiseaseSymptom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseSymptomRepository extends CrudRepository<DiseaseSymptom, DiseaseSymptomKey> {
    List<DiseaseSymptom> findAll();

    void deleteByDiseaseIdAndSymptomId(int diseaseId, int symptomId);

}
