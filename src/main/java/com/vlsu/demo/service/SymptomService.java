package com.vlsu.demo.service;

import com.vlsu.demo.model.entity.Disease;
import com.vlsu.demo.model.entity.Symptom;
import com.vlsu.demo.model.repository.SymptomRepository;
import com.vlsu.demo.model.restObject.SymptomWithDiseases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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

    public List<SymptomWithDiseases> getAllCommonInfo(){
        Query symptomQuery = em.createQuery("select s from Symptom s");
        List symptomList = symptomQuery.getResultList();
        Query diseaseQuery = em.createQuery("select d, ds.symptomId from Disease d " +
                "inner join DiseaseSymptom ds on ds.diseaseId = d.diseaseId");
        List diseaseList = diseaseQuery.getResultList();
        return mapToSymptomWithDiseases(symptomList,diseaseList);
    }

    private List<SymptomWithDiseases> mapToSymptomWithDiseases(List symptomList, List diseaseList) {
        ArrayList<SymptomWithDiseases> result = new ArrayList<>();
        for (Object o : symptomList) {
            int symptomId = ((Symptom) o).getSymptomId();
            ArrayList<Disease> tempDiseaseList = new ArrayList<>();
            for (Object entry : diseaseList) {
                if (((Object[]) entry)[1].equals(symptomId)) {
                    tempDiseaseList.add((Disease) ((Object[]) entry)[0]);
                }
            }
            result.add(new SymptomWithDiseases(
                    symptomId
                    , ((Symptom) o).getName()
                    , ((Symptom) o).getInfo()
                    , tempDiseaseList
            ));
        }
        return result;
    }

}
