package com.vlsu.demo.service;

import com.vlsu.demo.model.entity.Disease;
import com.vlsu.demo.model.entity.Medicament;
import com.vlsu.demo.model.entity.Symptom;
import com.vlsu.demo.model.repository.MedicamentRepository;
import com.vlsu.demo.model.restObject.MedsWithDiseases;
import com.vlsu.demo.model.restObject.SymptomWithDiseases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MedicamentService {

    private MedicamentRepository mr;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public MedicamentService(MedicamentRepository mr) {
        this.mr = mr;
    }

    public Collection<Medicament> getAll() {
        return mr.findAll();
    }

    public List<MedsWithDiseases> getAllCommonInfo() {
        Query medQuery = em.createQuery("select m from Medicament m");
        List medList = medQuery.getResultList();
        Query diseaseQuery = em.createQuery("select d, dm.medicamentId from Disease d " +
                "inner join DiseaseMed dm on dm.diseaseId = d.diseaseId");
        List diseaseList = diseaseQuery.getResultList();
        return mapToMedsWithDiseases(medList, diseaseList);
    }

    private List<MedsWithDiseases> mapToMedsWithDiseases(List medList, List diseaseList){
        ArrayList<MedsWithDiseases> result = new ArrayList<>();
        for (Object o : medList) {
            int medicamentId = ((Medicament) o).getMedicamentId();
            ArrayList<Disease> tempDiseaseList = new ArrayList<>();
            for (Object entry : diseaseList) {
                if (((Object[]) entry)[1].equals(medicamentId)) {
                    tempDiseaseList.add((Disease) ((Object[]) entry)[0]);
                }
            }
            result.add(new MedsWithDiseases(
                    medicamentId
                    , ((Medicament) o).getName()
                    , ((Medicament) o).getInfo()
                    , tempDiseaseList
            ));
        }
        return result;
    }
}
