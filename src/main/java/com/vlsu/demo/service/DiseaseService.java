package com.vlsu.demo.service;

import com.vlsu.demo.model.restObject.DiseaseWithRate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiseaseService {

    @PersistenceContext
    EntityManager em;

    public ArrayList<DiseaseWithRate> getAllBySymptoms(String symptoms) {
        Query query = em.createNativeQuery("select distinct d.disease_id as diseaseId, d.name as name,d.info as info, " +
                "case when ifNull(mand.mand_count,0) = 0 then 0 else (mand.mand_rate/mand.mand_count) end as mandatoryRate, " +
                "case when ifNull(mand.mand_count,0) = 0 then 0 else (opt.opt_rate/opt.opt_count) end as optionRate " +
                "from online_doctor.Disease d " +
                "inner join " +
                    "online_doctor.disease_symptom ds on ds.disease_id=d.disease_id " +
                "left join " +
                    "(select sum(rate) as mand_rate, count(*) as mand_count,disease_id from online_doctor.disease_symptom " +
                        "where mandatory = 0 " +
                        "group by disease_id) " +
                    "as mand " +
                    "on d.disease_id=mand.disease_id " +
                "left join " +
                    "(select sum(rate) as opt_rate, count(*) as opt_count,disease_id from online_doctor.disease_symptom " +
                        "where mandatory=true " +
                        "group by disease_id) " +
                    "as opt " +
                    "on d.disease_id= opt.disease_id " +
                "where ds.symptom_id in (:symptoms)");
        List<Integer> parsedSymptoms = Arrays.stream(symptoms.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        query.setParameter("symptoms", parsedSymptoms);
        return mapToDiseaseWithRate(query.getResultList());
    }

    private ArrayList<DiseaseWithRate> mapToDiseaseWithRate(List list) {
        ArrayList<DiseaseWithRate> result = new ArrayList<>();
        for (Object o : list) {
            result.add(new DiseaseWithRate(
                    (int) (((Object[]) o)[0])
                    , ((String) ((Object[]) o)[1])
                    , ((String) ((Object[]) o)[2])
                    , ((Long) ((Object[]) o)[3])
                    , ((Long) ((Object[]) o)[4])));
        }
        return result;
    }
}
