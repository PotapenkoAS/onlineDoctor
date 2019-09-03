package com.vlsu.demo.service;

import com.vlsu.demo.model.entity.Disease;
import com.vlsu.demo.model.entity.DiseaseSymptom;
import com.vlsu.demo.model.entity.Medicament;
import com.vlsu.demo.model.entity.Symptom;
import com.vlsu.demo.model.repository.DiseaseRepository;
import com.vlsu.demo.model.restObject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiseaseService {

    @PersistenceContext
    private EntityManager em;

    private DiseaseRepository dr;

    @Autowired
    public DiseaseService(DiseaseRepository dr) {
        this.dr = dr;
    }

    public Collection<Disease> getAll() {
        return dr.findAll();
    }

    public List<DiseaseWithAllCommonInfo> getAllCommonInfo() {
        Query diseaseQuery = em.createQuery("select distinct d from Disease d");
        List diseaseList = diseaseQuery.getResultList();
        Query symptomQuery = em.createQuery("select distinct s,ds.diseaseId from Symptom s " +
                "inner join DiseaseSymptom ds on ds.symptomId=s.symptomId");
        List symptomList = symptomQuery.getResultList();
        Query medicamentQuery = em.createQuery("select distinct m, dm.diseaseId from Medicament m " +
                "inner join DiseaseMed dm on dm.medicamentId = m.medicamentId");
        List medicamentList = medicamentQuery.getResultList();
        return mapToDiseaseWithAllCommonInfo(diseaseList, symptomList, medicamentList);
    }

    //Получает все болезни по симптомам, остортированные по вероятностям
    public ArrayList<DiseaseWithMeds> getAllBySymptoms(String symptoms) { //symptoms- строка в виде "1,2,3"
        Query query = em.createNativeQuery("select distinct d.disease_id as diseaseId, d.name as diseaseName,d.info as diseaseInfo, " +
                //case для того, чтобы правильно записывался 0, если не найдены проценты или количества
                "IF(ifNull(mand.mand_count,0) = 0, 0, (mand.mand_rate/mand.mand_count)) as mandatoryRate, " +
                "IF(ifNull(opt.opt_count,0) = 0, 0, (opt.opt_rate/opt.opt_count)) as optionRate " +
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

    public DiseaseWithAllInfo getDiseaseWithAllInfoById(int diseaseId) {
        DiseaseWithAllInfo result = new DiseaseWithAllInfo();
        Disease disease = dr.findByDiseaseId(diseaseId);
        List<SymptomWithRate> symptomList = disease.getDiseaseSymptomsByDiseaseId()
                .stream()
                .map(ds ->
                        new SymptomWithRate(
                                ds.getSymptomId()
                                , ds.getSymptomBySymptomId().getName()
                                , ds.getSymptomBySymptomId().getInfo()
                                , ds.getRate()
                                , ds.getMandatory()
                                , ds.getDiseaseId()
                        )
                )
                .collect(Collectors.toList());
        List<MedicamentWithRate> medicamentList = disease.getDiseaseMedsByDiseaseId()
                .stream()
                .map(md ->
                        new MedicamentWithRate(
                                md.getMedicamentId()
                                , md.getMedicamentByMedicamentId().getName()
                                , md.getMedicamentByMedicamentId().getInfo()
                                , md.getRate()
                                , md.getDiseaseId()
                        )
                )
                .collect(Collectors.toList());
        result.setDiseaseId(disease.getDiseaseId());
        result.setName(disease.getName());
        result.setInfo(disease.getInfo());
        result.setSymptomWithRateList(symptomList);
        result.setMedicamentWithRateList(medicamentList);
        return result;
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

    private ArrayList<DiseaseWithAllCommonInfo> mapToDiseaseWithAllCommonInfo(List diseaseList, List symptomList, List medicamentList) {
        ArrayList<DiseaseWithAllCommonInfo> result = new ArrayList<>();
        for (Object o : diseaseList) {
            int diseaseId = ((Disease) o).getDiseaseId();
            ArrayList<Symptom> tempSymptomList = new ArrayList<>();
            ArrayList<Medicament> tempMedicamentList = new ArrayList<>();
            for (Object entry : symptomList) {
                if (((Object[]) entry)[1].equals(diseaseId)) {
                    tempSymptomList.add((Symptom) ((Object[]) entry)[0]);
                }
            }
            for (Object entry : medicamentList) {
                if (((Object[]) entry)[1].equals(diseaseId)) {
                    tempMedicamentList.add((Medicament) ((Object[]) entry)[0]);
                }
            }
            result.add(new DiseaseWithAllCommonInfo(
                    diseaseId
                    , ((Disease) o).getName()
                    , ((Disease) o).getInfo()
                    , tempMedicamentList
                    , tempSymptomList
            ));
        }
        return result;
    }
}
