package com.adrian.champlonshipfootball.controller;

import com.adrian.champlonshipfootball.dtos.LeaderboardDto;
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

    @GetMapping("/leaderboards")
    public List<LeaderboardDto> getLeaderboard() throws Exception {
        try {
            return leaderboardService.findAllLeaderboards();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @GetMapping("/leaderboards/{id}")
    public LeaderboardDto getLeaderboardById(@PathVariable Long id) throws Exception {
        try {
            return leaderboardService.findLeaderboardById(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @GetMapping("/leaderboards/season/{seasonId}/category/{categoryId}")
    public List<LeaderboardDto> getLeaderboardBySeasonAndCategory(@PathVariable Long seasonId, @PathVariable Long categoryId) throws Exception {
        try {
            return leaderboardService.findByCategoryAndSeason(seasonId, categoryId);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PostMapping("/leaderboards")
    public LeaderboardDto saveLeaderboard(@RequestBody LeaderboardDto leaderboard) throws Exception {
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

    @DeleteMapping("/leaderboards/{id}")
    public void deleteLeaderboard(@PathVariable Long id) throws Exception {
        try {
            leaderboardService.deleteLeaderboard(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

}
