package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.dtos.GoalDto;
import com.adrian.champlonshipfootball.dtos.MatchDto;
import com.adrian.champlonshipfootball.model.*;
import com.adrian.champlonshipfootball.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final CategoryRepository categoryRepository;
    private final SeasonRepository seasonRepository;
    private final GoalService goalService;


    public MatchService(MatchRepository matchRepository, TeamRepository teamRepository,
                        CategoryRepository categoryRepository, SeasonRepository seasonRepository,
                        GoalService goalService) {
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
        this.categoryRepository = categoryRepository;
        this.seasonRepository = seasonRepository;
        this.goalService = goalService;
    }

    public List<MatchDto> findAllMatches() {
        return matchRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MatchDto saveMatch(MatchDto matchDto) {
        Match match = convertToEntity(matchDto);
        Match savedMatch = matchRepository.save(match);
        return convertToDTO(savedMatch);
    }

    public MatchDto findMatchById(long id) {
        return matchRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public MatchDto updateMatchStatus(long id, String status) {

        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found"));
        match.setStatus(status);
        Match updatedMatch = matchRepository.save(match);

        return convertToDTO(updatedMatch);
    }

    public void deleteMatchById(long id) {
        matchRepository.deleteById(id);
    }

    // Conversión de DTO a Entidad
    private Match convertToEntity(MatchDto matchDto) {
        Match match = new Match();
        match.setMatchId(matchDto.getMatchId());
        match.setMatchDate(matchDto.getMatchDate());
        match.setResult(matchDto.getResult());
        match.setStatus(matchDto.getStatus());

        Team homeTeam = teamRepository.findById(matchDto.getHomeTeam())
                .orElseThrow(() -> new RuntimeException("Home Team not found"));
        Team awayTeam = teamRepository.findById(matchDto.getAwayTeam())
                .orElseThrow(() -> new RuntimeException("Away Team not found"));
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);

        Category category = categoryRepository.findById(matchDto.getCategory())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        match.setCategory(category);

        Season season = seasonRepository.findById(matchDto.getSeason())
                .orElseThrow(() -> new RuntimeException("Season not found"));
        match.setSeason(season);

        return match;
    }

    // Conversión de Entidad a DTO
    private MatchDto convertToDTO(Match match) {
        MatchDto matchDto = new MatchDto();
        matchDto.setMatchId(match.getMatchId());
        matchDto.setMatchDate(match.getMatchDate());
        matchDto.setResult(match.getResult());
        matchDto.setStatus(match.getStatus());

        matchDto.setHomeTeam(match.getHomeTeam().getTeamId());
        matchDto.setAwayTeam(match.getAwayTeam().getTeamId());
        matchDto.setCategory(match.getCategory().getCategoryId());
        matchDto.setSeason(match.getSeason().getSeasonId());

        return matchDto;
    }

    public List<GoalDto> getGoalsForMatch(MatchDto matchDto) {
        return goalService.getGoalsByMatch(matchDto.getMatchId());
    }
}
