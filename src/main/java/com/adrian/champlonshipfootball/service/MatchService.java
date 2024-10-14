package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Goal;
import com.adrian.champlonshipfootball.model.Match;
import com.adrian.champlonshipfootball.repository.GoalRepository;
import com.adrian.champlonshipfootball.repository.MatchRepository;

import java.util.List;

public class MatchService {
    MatchRepository matchRepository;
    GoalService goalService;
    LeaderboardService leaderboardService;
    public MatchService(MatchRepository matchRepository, GoalService goalService, LeaderboardService leaderboardService) {
        this.matchRepository = matchRepository;
        this.goalService = goalService;
        this.leaderboardService = leaderboardService;
    }
    public List<Match> findAllMatches() {
        return matchRepository.findAll();
    }
    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }
    public Match findMatchById(long id) {
        return matchRepository.findById(id).orElse(null);
    }
    public Match updateMatch(long id,Match match) {
        Match matchToUpdate = findMatchById(id);
        matchToUpdate.setMatchDate(match.getMatchDate());
        matchToUpdate.setHomeTeam(match.getHomeTeam());
        matchToUpdate.setAwayTeam(match.getAwayTeam());
        matchToUpdate.setCategory(match.getCategory());
        matchToUpdate.setResult(match.getResult());
        matchToUpdate.setStatus(match.getStatus());
        matchToUpdate.setSeason(match.getSeason());
        return matchRepository.save(matchToUpdate);
    }
    public Match updateStatusMatch(long id, String status){
        List<String> state = List.of("Pendiente", "Jugandose", "Finalizado");
        if (!state.contains(status)) {
            return null;
        }
        Match updatedMatch = findMatchById(id);
        updatedMatch.setStatus(status);
        updatedMatch.setResult(updateResult(id));
        return matchRepository.updateByStatus(id, updatedMatch);
    }
    public String updateResult(long id){
        Match updatedMatch = findMatchById(id);
        List<Goal> goals = goalService.getGoalByMatch(id);
        int goalTeamOne = 0;
        int goalsTeamTwo = 0;
        String result;
        for (Goal goal : goals) {
            if (goal.getTeam().equals(updatedMatch.getHomeTeam())) {
                goalTeamOne++;
            } else if (goal.getTeam().equals(updatedMatch.getAwayTeam())) {
                goalsTeamTwo++;
            }
        }
        result = String.valueOf(goalTeamOne).concat(" - ").concat(String.valueOf(goalsTeamTwo));
        updatedMatch.setResult(result);
        leaderboardService.updateStatusTeam(updatedMatch, updatedMatch.getSeason());
        return matchRepository.updateByResult(id, updatedMatch);
    }
    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}
