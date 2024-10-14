package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Season;
import com.adrian.champlonshipfootball.repository.SeasonRepository;

import java.util.List;

public class SeasonService {
    SeasonRepository seasonRepository;
    public SeasonService(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }
    public Season createSeason(Season season) {
        return seasonRepository.save(season);
    }
    public List<Season> getSeasons() {
        return seasonRepository.findAll();
    }
    public Season getSeason(long id) {
        return seasonRepository.findById(id).orElse(null);
    }
    public Season updateSeason(long id, Season season) {
        Season seasonToUpdate = getSeason(id);
        seasonToUpdate.setSeasonName(season.getSeasonName());
        return seasonRepository.save(season);
    }
    public void deleteSeason(long id) {
        seasonRepository.deleteById(id);
    }
}
