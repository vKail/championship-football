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
    public List<Season> getSeasons() throws Exception {
        try {
            return seasonService.findSeasons();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @GetMapping("/seasons/{id}")
    public Season getSeasonById(@PathVariable Long id) throws Exception {
        try {
            return seasonService.findSeason(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PostMapping("/seasons")
    public Season saveSeason(@RequestBody Season season) throws Exception {
        try {
            return seasonService.createSeason(season);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PutMapping("/seasons/{id}")
    public Season updateSeason(@PathVariable Long id, @RequestBody Season season) throws Exception {
        try {
            return seasonService.updateSeason(id, season);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/seasons/{id}")
    public void deleteSeason(@PathVariable Long id) throws Exception {
        try {
            seasonService.deleteSeason(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }
}
