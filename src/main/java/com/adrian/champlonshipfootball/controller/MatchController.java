package com.adrian.champlonshipfootball.controller;

import com.adrian.champlonshipfootball.dtos.MatchDto;
import com.adrian.champlonshipfootball.model.Match;
import com.adrian.champlonshipfootball.service.MatchResultService;
import com.adrian.champlonshipfootball.service.MatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MatchController {
    private final MatchService matchService;
    private final MatchResultService matchResultService;


    public MatchController(MatchService matchService, MatchResultService matchResultService) {
        this.matchService = matchService;
        this.matchResultService = matchResultService;
    }

    @GetMapping("/matches")
    public List<MatchDto> getMatches() throws Exception {
        try {
            return matchService.findAllMatches();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @GetMapping("/matches/{id}")
    public MatchDto getMatchById(@PathVariable Long id) throws Exception {
        try {
            return matchService.findMatchById(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PostMapping("/matches")
    public MatchDto saveMatch(@RequestBody MatchDto match) throws Exception {
        try {
            return matchService.saveMatch(match);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PutMapping("/matches/{id}")
    public String updateStatusMatch(@PathVariable Long id, @RequestBody Map<String, String> body) throws Exception {
        try {
            String status = body.get("status");
            matchService.updateMatchStatus(id, status);

            if (status.equalsIgnoreCase("Finalizado")) {
                matchResultService.updateResultAndLeaderboard(id);
            }
            return "Match status updated";
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }


    @DeleteMapping("/matches/{id}")
    public void deleteMatch(@PathVariable Long id) throws Exception {
        try {
            matchService.deleteMatchById(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }
}
