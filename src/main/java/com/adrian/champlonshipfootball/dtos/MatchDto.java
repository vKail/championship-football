package com.adrian.champlonshipfootball.dtos;

import java.time.LocalDate;
import java.util.Date;

public class MatchDto {
    private long matchId;
    private long homeTeam;
    private long awayTeam;
    private long category;
    private long season;
    private LocalDate matchDate;
    private String result;
    private String status;

    public MatchDto() {
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public long getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(long homeTeam) {
        this.homeTeam = homeTeam;
    }

    public long getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(long awayTeam) {
        this.awayTeam = awayTeam;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    public long getSeason() {
        return season;
    }

    public void setSeason(long season) {
        this.season = season;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
