package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Leaderboard;
import com.adrian.champlonshipfootball.repository.LeaderboardRepository;

import java.util.List;

public class LeaderboardService {
    LeaderboardRepository leaderboardRepository;
    public LeaderboardService(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }
    public Leaderboard saveLeaderboard(Leaderboard leaderboard) {
        return leaderboardRepository.save(leaderboard);
    }
    public List<Leaderboard> findAllLeaderboard() {
        return leaderboardRepository.findAll();
    }
    public Leaderboard findLeaderboardById(long id) {
        return leaderboardRepository.findById(id).orElse(null);
    }
    public Leaderboard updateLeaderboard(long id, Leaderboard leaderboard) {
        Leaderboard oldLeaderboard = findLeaderboardById(id);
        oldLeaderboard.setTeam(leaderboard.getTeam());
        oldLeaderboard.setPoints(leaderboard.getPoints());
        oldLeaderboard.setSeason(leaderboard.getSeason());
        oldLeaderboard.setMatchesWon(leaderboard.getMatchesWon());
        oldLeaderboard.setMatchesDrawn(leaderboard.getMatchesDrawn());
        oldLeaderboard.setMatchesLost(leaderboard.getMatchesLost());
        oldLeaderboard.setGoalsScored(leaderboard.getGoalsScored());
        return leaderboardRepository.save(oldLeaderboard);
    }
    public void deleteLeaderboard(long id) {
        leaderboardRepository.deleteById(id);
    }
}
