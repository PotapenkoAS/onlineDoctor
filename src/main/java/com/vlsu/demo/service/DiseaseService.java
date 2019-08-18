package com.vlsu.demo.service;

import com.vlsu.demo.model.entity.Medicament;
import com.vlsu.demo.model.restObject.DiseaseWithMeds;
import com.vlsu.demo.model.restObject.MedicamentWithRate;
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

    //Получает все болезни по симптомам, остортированные по вероятностям
    public ArrayList<DiseaseWithMeds> getAllBySymptoms(String symptoms) { //symptoms- строка в виде "1,2,3"
        Query query = em.createNativeQuery("select distinct d.disease_id as diseaseId, d.name as diseaseName,d.info as diseaseInfo, " +
                //case для того, чтобы правильно записывался 0, если не найдены проценты или количества
                "case when ifNull(mand.mand_count,0) = 0 then 0 else (mand.mand_rate/mand.mand_count) end as mandatoryRate, " +
                "case when ifNull(opt.opt_count,0) = 0 then 0 else (opt.opt_rate/opt.opt_count) end as optionRate " +
                "from online_doctor.Disease d " +
                "inner join " +
                    "online_doctor.disease_symptom ds on ds.disease_id=d.disease_id " +
                //join для поиска обязательного процента
                "left join " +
                    "(select sum(rate) as mand_rate, count(*) as mand_count,disease_id from online_doctor.disease_symptom " +
                        "where mandatory = 0 " +
                        "and symptom_id in (:symptoms) " +
                        "group by disease_id) " +
                    "as mand " +
                    "on d.disease_id=mand.disease_id " +
                // join для поиска необязательного процента
                "left join " +
                    "(select sum(rate) as opt_rate, count(*) as opt_count,disease_id from online_doctor.disease_symptom " +
                        "where mandatory=1 " +
                        "and symptom_id in (:symptoms) " +
                        "group by disease_id) " +
                    "as opt " +
                    "on d.disease_id= opt.disease_id " +
                "where ds.symptom_id in (:symptoms) " +
                "order by mandatoryRate desc ");
        //парсим строку в лист чисел, чтоб модно было подставить в качестве параметра в запрос
        List<Integer> parsedSymptoms = Arrays.stream(symptoms.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        query.setParameter("symptoms", parsedSymptoms);
        List diseaseWithRates = query.setMaxResults(10).getResultList();
        // запрос для получения медикаментов для каждой найденной болезни, сортированные по процентам
        query = em.createQuery("select distinct m.medicamentId as medicamentId, m.name as name, m.info as info, dm.rate as rate, dm.diseaseId as diseaseId from Medicament m " +
                "inner join DiseaseMed dm on m.medicamentId = dm.medicamentId " +
                "inner join DiseaseSymptom ds on ds.diseaseId = dm.diseaseId " +
                "where ds.symptomId in (:symptoms) and ds.diseaseId in (:diseases)" +
                "order by  diseaseId, rate");
        query.setParameter("symptoms", parsedSymptoms);
        query.setParameter("diseases", collectDiseases(diseaseWithRates));
        List medicaments = query.getResultList();
        return mapToDiseaseWithRate(diseaseWithRates, medicaments);
    }

    private List<Integer> collectDiseases(List list) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Object o : list) {
            result.add((int) (((Object[]) o)[0]));
        }
        return result;
    }

    // маппинг двух полученных выборок в единый лист
    private ArrayList<DiseaseWithMeds> mapToDiseaseWithRate(List diseasesList, List medicamentsList) {
        ArrayList<DiseaseWithMeds> result = new ArrayList<>();
        //маппинг болезни
        for (Object o : diseasesList) {
            int diseaseId = (int) (((Object[]) o)[0]);
            ArrayList<MedicamentWithRate> tempMedList = new ArrayList<>();
            //маппинг медикаментов для каждой болезни
            for (Object entry : medicamentsList) {
                if (((Object[]) entry)[4].equals(diseaseId)) {
                    tempMedList.add(new MedicamentWithRate(
                            ((int) ((Object[]) entry)[0])
                            , ((String) ((Object[]) entry)[1])
                            , ((String) ((Object[]) entry)[2])
                            , ((Double) ((Object[]) entry)[3])
                            , ((int) ((Object[]) entry)[4])));
                }
            }
            result.add(new DiseaseWithMeds(
                    ((int) ((Object[]) o)[0])
                    , ((String) ((Object[]) o)[1])
                    , ((String) ((Object[]) o)[2])
                    , ((Double) ((Object[]) o)[3])
                    , ((Double) ((Object[]) o)[4])
                    , tempMedList
            ));
        }
        return result;
    }
}
