package com.adrian.champlonshipfootball.model;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Football_match")
public class Match {
    @Id
    @GeneratedValue
    @Column(name = "match_id", nullable = false)
    private long matchId;
    @ManyToOne
    @JoinColumn(name = "team_1", referencedColumnName = "team_id", nullable = false)
    private Team homeTeam;
    @ManyToOne
    @JoinColumn(name = "team_2", referencedColumnName = "team_id", nullable = false)
    private Team awayTeam;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    private Category category;
    @OneToOne
    @JoinColumn(name = "season_id", referencedColumnName = "season_id", nullable = false)
    private Season season;
//    @ManyToOne
//    @JoinColumn(name = "users_id", referencedColumnName = "users_id", nullable = false)
//    private Users usersId;
    @Column(name = "date", nullable = false)
    private Date matchDate;
    @Column(name = "result")
    private String result;
    @Column(name = "status")
    private String status;

    public Match() {}

    public Match(long matchId, Team homeTeam, Team awayTeam, Category category, Date matchDate, String result, String status, Season season) {
        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.category = category;
        this.matchDate = matchDate;
        this.result = result;
        this.status = status;
        this.season = season;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
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

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
}
