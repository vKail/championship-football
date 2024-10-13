package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Match;
import com.adrian.champlonshipfootball.repository.MatchRepository;

import java.util.List;

public class MatchService {
    MatchRepository matchRepository;
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
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
        return matchRepository.save(matchToUpdate);
    }
    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}
