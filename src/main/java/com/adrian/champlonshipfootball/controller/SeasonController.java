package com.adrian.champlonshipfootball.controller;

import com.adrian.champlonshipfootball.model.Season;
import com.adrian.champlonshipfootball.service.SeasonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeasonController {
    private final SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping("/seasons")
    public List<Season> getSeasons() {
        return seasonService.findSeasons();
    }

    @GetMapping("/seasons/{id}")
    public Season getSeasonById(@PathVariable Long id) {
        return seasonService.findSeason(id);
    }

    @PostMapping("/seasons")
    public Season saveSeason(@RequestBody Season season) {
        return seasonService.createSeason(season);
    }

    @PutMapping("/seasons/{id}")
    public Season updateSeason(@PathVariable Long id, @RequestBody Season season) {
        return seasonService.updateSeason(id, season);
    }

    @DeleteMapping("/seasons/{id}")
    public void deleteSeason(@PathVariable Long id) {
        seasonService.deleteSeason(id);
    }
}
