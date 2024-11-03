package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.dtos.DtDto;
import com.adrian.champlonshipfootball.model.Dt;
import com.adrian.champlonshipfootball.model.Team;
import com.adrian.champlonshipfootball.repository.DtRepository;
import com.adrian.champlonshipfootball.repository.TeamRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DtService {
    private final DtRepository dtRepository;
    private final TeamRepository teamRepository;

    public DtService(DtRepository dtRepository, TeamRepository teamRepository) {
        this.dtRepository = dtRepository;
        this.teamRepository = teamRepository;

    }

    public List<DtDto> findAllDts() {
        return dtRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DtDto findDtById(long id) {
        return dtRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public DtDto saveDt(DtDto dtDto) {
        Dt dt = convertToEntity(dtDto);
        Dt savedDt = dtRepository.save(dt);
        return convertToDTO(savedDt);
    }

    public DtDto updateDt(long id, DtDto dtDto) {
        Dt existingDt = dtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dt not found"));

        updateDtFromDTO(existingDt, dtDto);
        Dt updatedDt = dtRepository.save(existingDt);
        return convertToDTO(updatedDt);
    }

    public void deleteDt(long id) {
        dtRepository.deleteById(id);
    }

    public DtDto convertToDTO (Dt dt){
        DtDto dtDto = new DtDto();
        dtDto.setDni(dt.getDni());
        dtDto.setFirstname(dt.getFirstname());
        dtDto.setLastname(dt.getLastname());
        if (dt.getTeam() != null) {
            dtDto.setTeamId(dt.getTeam().getTeamId());
            dtDto.setTeamName(dt.getTeam().getName());
        }
        return  dtDto;
    }

    public Dt convertToEntity(DtDto dtDto){
        Dt dt = new Dt();
        dt.setDni(dtDto.getDni());
        dt.setFirstname(dtDto.getFirstname());
        dt.setLastname(dtDto.getLastname());
        if (dtDto.getTeamId() != null) {
            Team team = teamRepository.findById(dtDto.getTeamId()).
                    orElseThrow(() -> new RuntimeException("Team not found"));
            dt.setTeam(team);
        }

        return dt;
    }

    private void updateDtFromDTO(Dt dt, DtDto dtDto) {
        dt.setDni(dtDto.getDni());
        dt.setFirstname(dtDto.getFirstname());
        dt.setLastname(dtDto.getLastname());
        if (dtDto.getTeamId() != null) {
            Team team = teamRepository.findById(dtDto.getTeamId())
                    .orElseThrow(() -> new RuntimeException("Team not found"));
            dt.setTeam(team);
        }
    }
}
