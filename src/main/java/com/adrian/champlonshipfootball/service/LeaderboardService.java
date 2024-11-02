package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.dtos.LeaderboardDto;
import com.adrian.champlonshipfootball.dtos.MatchDto;
import com.adrian.champlonshipfootball.dtos.TeamDto;
import com.adrian.champlonshipfootball.model.Leaderboard;
import com.adrian.champlonshipfootball.model.Match;
import com.adrian.champlonshipfootball.model.Season;
import com.adrian.champlonshipfootball.model.Team;
import com.adrian.champlonshipfootball.repository.LeaderboardRepository;
import com.adrian.champlonshipfootball.repository.MatchRepository;
import com.adrian.champlonshipfootball.repository.SeasonRepository;
import com.adrian.champlonshipfootball.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final SeasonRepository seasonRepository;
    private final MatchService matchService;
    private final TeamService teamService;
    private final SeasonService seasonService;

    public LeaderboardService(LeaderboardRepository leaderboardRepository, MatchRepository matchRepository,
                              TeamRepository teamRepository, SeasonRepository seasonRepository, MatchService matchService, TeamService teamService, SeasonService seasonService) {
        this.leaderboardRepository = leaderboardRepository;
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.seasonRepository = seasonRepository;
        this.matchService = matchService;
        this.teamService = teamService;
        this.seasonService = seasonService;
    }

    public void updateLeaderboardAfterMatch(MatchDto match) {
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

    public LeaderboardDto saveLeaderboard(LeaderboardDto leaderboard) {
        Leaderboard entity = convertToEntity(leaderboard);
        Leaderboard savedLeaderboard = leaderboardRepository.save(entity);
        return convertToDTO(savedLeaderboard);
    }

    public List<LeaderboardDto> findAllLeaderboards() {
        return leaderboardRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public LeaderboardDto findLeaderboardById(long id) {
        return leaderboardRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public Leaderboard findByTeamAndSeason(long team, long season) {
        return leaderboardRepository.findByTeamTeamIdAndSeasonSeasonId(team, season);
    }


    private LeaderboardDto convertToDTO(Leaderboard leaderboard) {
        LeaderboardDto dto = new LeaderboardDto();
        dto.setLeaderboardId(leaderboard.getLeaderboardId());
        dto.setTeamId(leaderboard.getTeam().getTeamId());
        dto.setTeamName(leaderboard.getTeam().getName());
        dto.setSeasonId(leaderboard.getSeason().getSeasonId());
        dto.setSeasonName(leaderboard.getSeason().getSeasonName());
        dto.setPoints(leaderboard.getPoints());
        dto.setMatchesWon(leaderboard.getMatchesWon());
        dto.setMatchesLost(leaderboard.getMatchesLost());
        dto.setMatchesDrawn(leaderboard.getMatchesDrawn());
        dto.setGoalsScored(leaderboard.getGoalsScored());
        return dto;
    }

    private Leaderboard convertToEntity(LeaderboardDto dto) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.setLeaderboardId(dto.getLeaderboardId());
        Team team = teamRepository.findById(dto.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));
        Season season = seasonRepository.findById(dto.getSeasonId())
                .orElseThrow(() -> new RuntimeException("Season not found"));
        leaderboard.setTeam(team);
        leaderboard.setSeason(season);
        leaderboard.setPoints(dto.getPoints());
        leaderboard.setMatchesWon(dto.getMatchesWon());
        leaderboard.setMatchesLost(dto.getMatchesLost());
        leaderboard.setMatchesDrawn(dto.getMatchesDrawn());
        leaderboard.setGoalsScored(dto.getGoalsScored());
        return leaderboard;
    }

    public void deleteLeaderboard(long id) {
        leaderboardRepository.deleteById(id);
    }
}

