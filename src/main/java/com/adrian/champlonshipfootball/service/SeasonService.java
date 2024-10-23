package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Season;
import com.adrian.champlonshipfootball.repository.SeasonRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SeasonService {
    private final SeasonRepository seasonRepository;
    public SeasonService(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }
    public Season createSeason(Season season) {
        return seasonRepository.save(season);
    }
    public List<Season> findSeasons() {
        return seasonRepository.findAll();
    }
    public Season findSeason(long id) {
        return seasonRepository.findById(id).orElse(null);
    }
    public Season updateSeason(long id, Season season) {
        Season seasonToUpdate = findSeason(id);
        seasonToUpdate.setSeasonName(season.getSeasonName());
        return seasonRepository.save(season);
    }
    public void deleteSeason(long id) {
        seasonRepository.deleteById(id);
    }
}
