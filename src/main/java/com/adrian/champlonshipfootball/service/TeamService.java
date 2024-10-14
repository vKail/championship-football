package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Team;
import com.adrian.champlonshipfootball.repository.TeamRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }
    public Team findTeamById(long id) {
        return teamRepository.findById(id).orElse(null);
    }
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }
    public Team updateTeam(long id,Team team) {
        Team teamToUpdate = findTeamById(id);
        teamToUpdate.setName(team.getName());
        return teamRepository.save(team);
    }
    public void deleteTeam(long id) {
        teamRepository.deleteById(id);
    }
}
