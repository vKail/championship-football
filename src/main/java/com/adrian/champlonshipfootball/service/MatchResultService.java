package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Goal;
import com.adrian.champlonshipfootball.model.Match;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchResultService {
    private final MatchService matchService;
    private final LeaderboardService leaderboardService;

    public MatchResultService(MatchService matchService, LeaderboardService leaderboardService) {
        this.matchService = matchService;
        this.leaderboardService = leaderboardService;
    }

    public String updateResultAndLeaderboard(long matchId) {
        Match match = matchService.findMatchById(matchId);
        if (match == null || !match.getStatus().equals("Finalizado")) {
            System.out.println("Match not finished");
            return null;
        }

        List<Goal> goals = matchService.getGoalsForMatch(match);
        String result = calculateMatchResult(goals, match);
        match.setResult(result);

        matchService.saveMatch(match);
        leaderboardService.updateLeaderboardAfterMatch(match);
        return "Puntaje actualizado".concat(match.getHomeTeam().toString()).concat(" - ").concat(match.getAwayTeam().toString()
                .concat(" ").concat(result));
    }

    private String calculateMatchResult(List<Goal> goals, Match match) {
        int homeGoals = 0;
        int awayGoals = 0;

        for (Goal goal : goals) {
            if (goal.getTeam().equals(match.getHomeTeam())) {
                homeGoals++;
            } else if (goal.getTeam().equals(match.getAwayTeam())) {
                awayGoals++;
            }
        }

        return homeGoals + " - " + awayGoals;
    }
}
