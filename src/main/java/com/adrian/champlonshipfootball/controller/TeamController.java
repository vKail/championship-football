package com.adrian.champlonshipfootball.controller;

import com.adrian.champlonshipfootball.model.Team;
import com.adrian.champlonshipfootball.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public List<Team> getTeams() throws Exception {
        try {
            return teamService.findAllTeams();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @GetMapping("/teams/{id}")
    public Team getTeamById(@PathVariable Long id) throws Exception {
        try {
            return teamService.findTeamById(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PostMapping("/teams")
    public Team saveTeam(@RequestBody Team team) throws Exception {
        try {
            return teamService.saveTeam(team);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PutMapping("/teams/{id}")
    public Team updateTeam(@PathVariable Long id, @RequestBody Team team) throws Exception {
        try {
            return teamService.updateTeam(id, team);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/teams/{id}")
    public void deleteTeam(@PathVariable Long id) throws Exception {
        try {
            teamService.deleteTeam(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }
}
