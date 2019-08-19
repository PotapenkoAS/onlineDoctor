package com.vlsu.demo.service;

import com.vlsu.demo.model.entity.TestSymptom;
import com.vlsu.demo.model.repository.TestSymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestSymptomService {

    private TestSymptomRepository testSymptomRepository;

    @Autowired
    public TestSymptomService(TestSymptomRepository testSymptomRepository) {
        this.testSymptomRepository = testSymptomRepository;
    }

    @Transactional
    public boolean save(int testId, int symptomId) {
        try {
            testSymptomRepository.save(new TestSymptom(testId, symptomId));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
