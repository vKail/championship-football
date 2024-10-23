package com.adrian.champlonshipfootball.controller;

import com.adrian.champlonshipfootball.model.Leaderboard;
import com.adrian.champlonshipfootball.model.Match;
import com.adrian.champlonshipfootball.model.Season;
import com.adrian.champlonshipfootball.service.LeaderboardService;
import com.adrian.champlonshipfootball.service.MatchResultService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeaderboardController {
    private final LeaderboardService leaderboardService;
    private final MatchResultService matchResultService;

    public LeaderboardController(LeaderboardService leaderboardService, MatchResultService matchResultService) {
        this.leaderboardService = leaderboardService;
        this.matchResultService = matchResultService;
    }

    @GetMapping("/leaderboard")
    public List<Leaderboard> getLeaderboard() throws Exception {
        try {
            return leaderboardService.findAllLeaderboards();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @GetMapping("/leaderboard/{id}")
    public Leaderboard getLeaderboardById(@PathVariable Long id) throws Exception {
        try {
            return leaderboardService.findLeaderboardById(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PostMapping("/leaderboard")
    public Leaderboard saveLeaderboard(@RequestBody Leaderboard leaderboard) throws Exception {
        try {
            return leaderboardService.saveLeaderboard(leaderboard);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PutMapping("/leaderboards/{id}")
    public String updateLeaderboard(@PathVariable long id ) throws Exception {
        try {
            return matchResultService.updateResultAndLeaderboard(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/leaderboard/{id}")
    public void deleteLeaderboard(@PathVariable Long id) throws Exception {
        try {
            leaderboardService.deleteLeaderboard(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

}
