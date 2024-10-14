package com.adrian.champlonshipfootball.controller;

import com.adrian.champlonshipfootball.model.Leaderboard;
import com.adrian.champlonshipfootball.model.Match;
import com.adrian.champlonshipfootball.model.Season;
import com.adrian.champlonshipfootball.service.LeaderboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeaderboardController {
    private final LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/leaderboard")
    public List<Leaderboard> getLeaderboard() {
        return leaderboardService.findAllLeaderboard();
    }

    @GetMapping("/leaderboard/{id}")
    public Leaderboard getLeaderboardById(@PathVariable Long id) {
        return leaderboardService.findLeaderboardById(id);
    }

    @PostMapping("/leaderboard")
    public Leaderboard saveLeaderboard(@RequestBody Leaderboard leaderboard) {
        return leaderboardService.saveLeaderboard(leaderboard);
    }

    @PutMapping("/leaderboard/{match}")
    public void updateLeaderboard(@RequestBody Match match, @RequestBody Season season) {
        leaderboardService.updateStatusTeam(match, season);
    }

    @DeleteMapping("/leaderboard/{id}")
    public void deleteLeaderboard(@PathVariable Long id) {
        leaderboardService.deleteLeaderboard(id);
    }
}
