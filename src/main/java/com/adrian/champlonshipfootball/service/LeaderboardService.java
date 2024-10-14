package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Leaderboard;
import com.adrian.champlonshipfootball.model.Match;
import com.adrian.champlonshipfootball.model.Season;
import com.adrian.champlonshipfootball.model.Team;
import com.adrian.champlonshipfootball.repository.LeaderboardRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LeaderboardService {
    private final LeaderboardRepository leaderboardRepository;
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
    public Leaderboard findByTeamAndSeason(Team team, Season season) {
        return leaderboardRepository.findByTeamAndSeason(team, season);
    }
    public void updateStatusTeam(Match match, Season season) {
        Leaderboard updatedHomeTeam = findByTeamAndSeason(match.getHomeTeam(), season);
        Leaderboard updatedAwayTeam = findByTeamAndSeason(match.getAwayTeam(), season);
        if (!match.getStatus().equalsIgnoreCase("Finalizado")) return ;
        updatedHomeTeam.setGoalsScored(updatedHomeTeam.getGoalsScored() + Integer.parseInt(match.getResult().split("-")[0]));
        updatedAwayTeam.setGoalsScored(updatedAwayTeam.getGoalsScored() + Integer.parseInt(match.getResult().split("-")[1]));
        if (Integer.parseInt(match.getResult().split("-")[0]) > Integer.parseInt(match.getResult().split("-")[1])) {
            updatedHomeTeam.setMatchesWon(updatedHomeTeam.getMatchesWon() + 1);
            updatedHomeTeam.setPoints(updatedHomeTeam.getPoints() + 3);
        } else if (Integer.parseInt(match.getResult().split("-")[0]) < Integer.parseInt(match.getResult().split("-")[1])) {
            updatedHomeTeam.setMatchesLost(updatedHomeTeam.getMatchesLost() + 1);
            updatedAwayTeam.setMatchesWon(updatedAwayTeam.getMatchesWon() + 1);
            updatedAwayTeam.setPoints(updatedAwayTeam.getPoints() + 3);
        } else {
            updatedHomeTeam.setMatchesDrawn(updatedHomeTeam.getMatchesDrawn() + 1);
            updatedAwayTeam.setMatchesDrawn(updatedAwayTeam.getMatchesDrawn() + 1);
            updatedHomeTeam.setPoints(updatedHomeTeam.getPoints() + 1);
            updatedAwayTeam.setPoints(updatedAwayTeam.getPoints() + 1);

        }
        leaderboardRepository.save(updatedHomeTeam);
        leaderboardRepository.save(updatedAwayTeam);

    }

    public void deleteLeaderboard(long id) {
        leaderboardRepository.deleteById(id);
    }
}
