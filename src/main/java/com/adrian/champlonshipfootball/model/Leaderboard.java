package com.adrian.champlonshipfootball.model;

import jakarta.persistence.*;

@Entity
@Table(name = "leaderboard")
public class Leaderboard {
    @Id
    @GeneratedValue
    @Column(name = "leaderboard_id", nullable = false)
    private int leaderboardId;
    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id", nullable = false)
    private Team team;
    @ManyToOne
    @JoinColumn(name = "season_id", referencedColumnName = "season_id", nullable = false)
    private Season season;
    @Column
    private int points;
    @Column(name = "matches_won")
    private int matchesWon;
    @Column(name = "matches_lost")
    private int matchesLost;
    @Column(name = "matches_drawn")
    private int matchesDrawn;
    @Column(name = "goals_scored")
    private int goalsScored;

    public Leaderboard() {
    }

    public Leaderboard(Team team, Season season, int points, int matchesWon, int matchesLost, int matchesDrawn, int goalsScored) {
        this.team = team;
        this.season = season;
        this.points = points;
        this.matchesWon = matchesWon;
        this.matchesLost = matchesLost;
        this.matchesDrawn = matchesDrawn;
        this.goalsScored = goalsScored;
    }

    public int getLeaderboardId() {
        return leaderboardId;
    }

    public void setLeaderboardId(int leaderboardId) {
        this.leaderboardId = leaderboardId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    public int getMatchesLost() {
        return matchesLost;
    }

    public void setMatchesLost(int matchesLost) {
        this.matchesLost = matchesLost;
    }

    public int getMatchesDrawn() {
        return matchesDrawn;
    }

    public void setMatchesDrawn(int matchesDrawn) {
        this.matchesDrawn = matchesDrawn;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }
}
