package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Goal;
import com.adrian.champlonshipfootball.model.Match;
import com.adrian.champlonshipfootball.repository.MatchRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final GoalService goalService;

    public MatchService( MatchRepository matchRepository, GoalService goalService) {
        this.matchRepository = matchRepository;
        this.goalService = goalService;
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

    public List<Goal> getGoalsForMatch(Match match) {
        return goalService.getGoalByMatch(match);
    }

    public Match updateMatchStatus(long id, String status) {
        List<String> states = List.of("Pendiente", "Jugandose", "Finalizado");
        if (!states.contains(status)) {
            return null;
        }

        Match updatedMatch = findMatchById(id);
        updatedMatch.setStatus(status);
        return matchRepository.save(updatedMatch);
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}
