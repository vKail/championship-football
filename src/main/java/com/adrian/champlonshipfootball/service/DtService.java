package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Dt;
import com.adrian.champlonshipfootball.repository.DtRepository;

import java.util.List;

public class DtService {
    DtRepository dtRepository;
    public DtService(DtRepository dtRepository) {
        this.dtRepository = dtRepository;
    }
    public List<Dt> findAllDt() {
        return dtRepository.findAll();
    }
    public Dt saveDt(Dt dt) {
        return dtRepository.save(dt);
    }
    public Dt findDtById(long id) {
        return dtRepository.findById(id).orElse(null);
    }
    public Dt updateDt(long id, Dt dt) {
        Dt oldDt = findDtById(id);
        oldDt.setDni(dt.getDni());
        oldDt.setFirstname(dt.getFirstname());
        oldDt.setLastname(dt.getLastname());
        oldDt.setTeam(dt.getTeam());
        return dtRepository.save(oldDt);
    }
    public void deleteDt(long id) {
        dtRepository.deleteById(id);
    }
}
