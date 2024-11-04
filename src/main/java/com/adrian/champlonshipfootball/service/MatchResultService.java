package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.dtos.GoalDto;
import com.adrian.champlonshipfootball.dtos.MatchDto;
import com.adrian.champlonshipfootball.model.Goal;
import com.adrian.champlonshipfootball.model.Leaderboard;
import com.adrian.champlonshipfootball.repository.GoalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchResultService {
    private final MatchService matchService;
    private final LeaderboardService leaderboardService;
    private final GoalRepository goalRepository;

    public MatchResultService(MatchService matchService, LeaderboardService leaderboardService,
                              GoalRepository goalRepository) {
        this.matchService = matchService;
        this.leaderboardService = leaderboardService;
        this.goalRepository = goalRepository;
    }

    public String updateResultAndLeaderboard(long matchId) {

        MatchDto matchDto = matchService.findMatchById(matchId);
        if (matchDto == null || !"Finalizado".equals(matchDto.getStatus())) {
            System.out.println("Match not finished");
            return null;
        }

        List<GoalDto> goals = matchService.getGoalsForMatch(matchDto);
        String result = calculateMatchResult(goals, matchDto);
        matchDto.setResult(result);

        matchService.updateMatch(matchDto);
        leaderboardService.updateLeaderboardAfterMatch(matchDto);

        return "Puntaje actualizado para " + matchDto.getHomeTeamName() + " - " + matchDto.getAwayTeamName() + " con resultado " + result;
    }

    // Método para calcular el resultado basado en los goles
    private String calculateMatchResult(List<GoalDto> goals, MatchDto matchDto) {
        int homeGoals = 0;
        int awayGoals = 0;

        for (GoalDto goal : goals) {
            if (goal.getTeamId() == matchDto.getHomeTeamId()) {
                homeGoals++;
            } else if (goal.getTeamId() == matchDto.getAwayTeamId()) {
                awayGoals++;
            }
        }

        return homeGoals + " - " + awayGoals;
    }
}

