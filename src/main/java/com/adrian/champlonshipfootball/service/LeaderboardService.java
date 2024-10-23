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

    public List<Leaderboard> findAllLeaderboards() {
        return leaderboardRepository.findAll();
    }

    public Leaderboard findLeaderboardById(long id) {
        return leaderboardRepository.findById(id).orElse(null);
    }

    public Leaderboard findByTeamAndSeason(Team team, Season season) {
        return leaderboardRepository.findByTeamAndSeason(team, season);
    }

    public void updateLeaderboardAfterMatch(Match match) {
        Leaderboard homeTeamLeaderboard = findByTeamAndSeason(match.getHomeTeam(), match.getSeason());
        Leaderboard awayTeamLeaderboard = findByTeamAndSeason(match.getAwayTeam(), match.getSeason());

        String[] result = match.getResult().split(" - ");
        int homeGoals = Integer.parseInt(result[0]);
        int awayGoals = Integer.parseInt(result[1]);

        homeTeamLeaderboard.setGoalsScored(homeTeamLeaderboard.getGoalsScored() + homeGoals);
        awayTeamLeaderboard.setGoalsScored(awayTeamLeaderboard.getGoalsScored() + awayGoals);

        if (homeGoals > awayGoals) {
            homeTeamLeaderboard.setMatchesWon(homeTeamLeaderboard.getMatchesWon() + 1);
            homeTeamLeaderboard.setPoints(homeTeamLeaderboard.getPoints() + 3);
            awayTeamLeaderboard.setMatchesLost(awayTeamLeaderboard.getMatchesLost() + 1);
        } else if (homeGoals < awayGoals) {
            awayTeamLeaderboard.setMatchesWon(awayTeamLeaderboard.getMatchesWon() + 1);
            awayTeamLeaderboard.setPoints(awayTeamLeaderboard.getPoints() + 3);
            homeTeamLeaderboard.setMatchesLost(homeTeamLeaderboard.getMatchesLost() + 1);
        } else {
            homeTeamLeaderboard.setMatchesDrawn(homeTeamLeaderboard.getMatchesDrawn() + 1);
            awayTeamLeaderboard.setMatchesDrawn(awayTeamLeaderboard.getMatchesDrawn() + 1);
            homeTeamLeaderboard.setPoints(homeTeamLeaderboard.getPoints() + 1);
            awayTeamLeaderboard.setPoints(awayTeamLeaderboard.getPoints() + 1);
        }

        leaderboardRepository.save(homeTeamLeaderboard);
        leaderboardRepository.save(awayTeamLeaderboard);
    }

    public void deleteLeaderboard(long id) {
        leaderboardRepository.deleteById(id);
    }
}
