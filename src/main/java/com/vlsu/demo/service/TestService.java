package com.vlsu.demo.service;

import com.vlsu.demo.model.entity.Symptom;
import com.vlsu.demo.model.entity.Test;
import com.vlsu.demo.model.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
public class TestService {

    @PersistenceContext
    private EntityManager em;

    private TestRepository testRepository;
    private TestSymptomService testSymptomService;
    private SymptomService symptomService;

    @Autowired
    public TestService(TestRepository testRepository, TestSymptomService testSymptomService, SymptomService symptomService) {
        this.testRepository = testRepository;
        this.testSymptomService = testSymptomService;
        this.symptomService = symptomService;
    }

    public Collection<Test> getAll() {
        return testRepository.findAll();
    }

    public Collection<Test> getAllByUserId(int userId) {
        return testRepository.findAllByUserIdOrderByDateDesc(userId);
    }


    @Transactional//метод сохранения теста для пользователя, а также создание новых записей в связочной таблице
    public boolean saveTest(int userId, String symptomIds) {
        try {
            //парсинг симптомов в лист integer
            List<Integer> parsedSymptoms = Arrays.stream(symptomIds.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            String collectedSymptomsInfo = collectSymptomsInfo(parsedSymptoms);
            Test test = testRepository.save(new Test(userId, collectedSymptomsInfo, new Timestamp(System.currentTimeMillis())));
            //последовательное сохранение каждого нового симптома для теста
            for (Integer item : parsedSymptoms) {
                if (!testSymptomService.save(test.getTestId(), item)) {
                    throw new Exception("testSymptom save failed");
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private String collectSymptomsInfo(List<Integer> parsedSymptoms) {
        List<Symptom> list = symptomService.getAllInIdList(parsedSymptoms);
        StringBuilder result = new StringBuilder();
        for(Symptom item: list){
            result.append("Название: ").append(item.getName()).append("; Описание: ").append(item.getInfo()).append(".\\n");
        }
        return result.toString();
    }

}
