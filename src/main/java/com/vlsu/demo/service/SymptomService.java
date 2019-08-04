package com.vlsu.demo.service;

import com.vlsu.demo.model.entity.Symptom;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SymptomService {

    @PersistenceContext
    private EntityManager em;

    public List<Symptom> getAll() {
        TypedQuery<Symptom> query = em.createQuery("SELECT s from Symptom s",Symptom.class);
        return query.getResultList();
    }

    public List<String> getAllInfo() {
        TypedQuery<String> query = em.createQuery("SELECT s.info from Symptom s",String.class);
        return query.getResultList();
    }

}
