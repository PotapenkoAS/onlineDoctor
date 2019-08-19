package com.vlsu.demo.service;

import com.vlsu.demo.model.entity.Symptom;
import com.vlsu.demo.model.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class SymptomService {

    @PersistenceContext
    private EntityManager em;

    private SymptomRepository symptomRepository;

    @Autowired
    public SymptomService(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    public List<Symptom> getAll() {
      return symptomRepository.findAll();
    }

    public List<String> getAllInfo() {
        TypedQuery<String> query = em.createQuery("SELECT s.info from Symptom s",String.class);
        return query.getResultList();
    }

    public List<Symptom> getAllInIdList(List<Integer> ids){
        return symptomRepository.findAllBySymptomIdIsIn(ids);
    }

}
