package com.vlsu.demo.service;

import com.vlsu.demo.model.entity.Disease;
import com.vlsu.demo.model.restObject.DiseaseWithRate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiseaseService {

    @PersistenceContext
    EntityManager em;

    public ArrayList<DiseaseWithRate> getAllBySymptoms(String symptoms) {
        Query query = em.createQuery("select d.diseaseId as diseaseId, d.name as name,d.info as info,sum(ds.rate) as rate " +
                "from Disease d " +
                "inner join DiseaseSymptom ds on ds.diseaseId=d.diseaseId " +
                "where ds.symptomId in :symptoms " +
                "group by d.diseaseId " +
                "order by rate desc");
        List<Integer> parsedSymptoms = Arrays.stream(symptoms.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        query.setParameter("symptoms", parsedSymptoms);
        return mapToDiseaseWithRate(query.getResultList());
    }

    private ArrayList<DiseaseWithRate> mapToDiseaseWithRate(List list) {
        ArrayList<DiseaseWithRate> result = new ArrayList<>();
        for (Object o : list) {
            result.add(new DiseaseWithRate(
                    (Long) (((Object[]) o)[0])
                    , ((String) ((Object[]) o)[1])
                    , ((String) ((Object[]) o)[2])
                    , ((Long) ((Object[]) o)[3])));
        }
        return result;
    }
}
