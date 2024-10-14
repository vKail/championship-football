package com.adrian.champlonshipfootball.controller;

import com.adrian.champlonshipfootball.model.Match;
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
    public List<Match> getMatches() {
        return matchService.findAllMatches();
    }

    @GetMapping("/matches/{id}")
    public Match getMatchById(@PathVariable Long id) {
        return matchService.findMatchById(id);
    }

    @PostMapping("/matches")
    public Match saveMatch(@RequestBody Match match) {
        return matchService.saveMatch(match);
    }

    @PutMapping("/matches/{id}")
    public Match updateMatch(@PathVariable Long id,@RequestBody String match) {
        return matchService.updateStatusMatch(id, match);
    }

    @DeleteMapping("/matches/{id}")
    public void deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
    }
}
