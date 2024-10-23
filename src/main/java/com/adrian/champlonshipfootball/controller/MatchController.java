package com.adrian.champlonshipfootball.controller;

import com.adrian.champlonshipfootball.model.Match;
import com.adrian.champlonshipfootball.service.MatchResultService;
import com.adrian.champlonshipfootball.service.MatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatchController {
    private final MatchService matchService;


    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/matches")
    public List<Match> getMatches() throws Exception {
        try {
            return matchService.findAllMatches();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @GetMapping("/matches/{id}")
    public Match getMatchById(@PathVariable Long id) throws Exception {
        try {
            return matchService.findMatchById(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PostMapping("/matches")
    public Match saveMatch(@RequestBody Match match) throws Exception {
        try {
            return matchService.saveMatch(match);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PutMapping("/matches/{id}")
    public Match updateMatch(@PathVariable Long id, @RequestBody String status) throws Exception {
        try {
            return matchService.updateMatchStatus(id, status);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/matches/{id}")
    public void deleteMatch(@PathVariable Long id) throws Exception {
        try {
            matchService.deleteMatch(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }
}
