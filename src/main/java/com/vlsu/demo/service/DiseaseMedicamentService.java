package com.vlsu.demo.service;

import com.vlsu.demo.model.entity.DiseaseMed;
import com.vlsu.demo.model.repository.DiseaseMedicamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiseaseMedicamentService {

    private DiseaseMedicamentRepository dmr;

    @Autowired
    public DiseaseMedicamentService(DiseaseMedicamentRepository dmr) {
        this.dmr = dmr;
    }

    @Transactional
    public DiseaseMed saveDiseaseMedicament(int medicamentId, int diseaseId, double rate) {
        DiseaseMed dm = new DiseaseMed();
        dm.setDiseaseId(diseaseId);
        dm.setMedicamentId(medicamentId);
        dm.setRate(rate);
        return dmr.save(dm);
    }
}
