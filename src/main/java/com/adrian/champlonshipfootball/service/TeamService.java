package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Team;
import com.adrian.champlonshipfootball.repository.TeamRepository;

import java.util.List;

public class TeamService {
    TeamRepository teamRepository;
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
    public Team getTeamById(long id) {
        return teamRepository.findById(id).orElse(null);
    }
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }
    public Team updateTeam(long id,Team team) {
        Team teamToUpdate = getTeamById(id);
        teamToUpdate.setName(team.getName());
        return teamRepository.save(team);
    }
    public void deleteTeam(long id) {
        teamRepository.deleteById(id);
    }
}
