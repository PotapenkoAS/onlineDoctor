package com.vlsu.demo.service;

import com.vlsu.demo.model.entity.Test;
import com.vlsu.demo.model.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Queue;

@Service
public class TestService {

    @PersistenceContext
    private EntityManager em;

    private TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Collection<Test> getAll() {
        return testRepository.findAll();
    }

    public Collection<Test> getAllByUserId(int userId) {
        return testRepository.findAllByUserIdOrderByDateDesc(userId);
    }


    @Transactional
    public boolean saveTest(int clientId, String symptoms) {
        try {
            testRepository.save(new Test(clientId, symptoms, new Timestamp(System.currentTimeMillis())));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
