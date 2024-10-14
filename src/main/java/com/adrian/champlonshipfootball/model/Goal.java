package com.adrian.champlonshipfootball.model;

import jakarta.persistence.*;

@Entity
@Table(name = "goals")
public class Goal {
    @Id
    @GeneratedValue
    @Column(name = "goals_id", nullable = false)
    private long goalId;
    @ManyToOne
    @JoinColumn(name = "match_id",  referencedColumnName = "match_id", nullable = false)
    private Match match;
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "player_id", nullable = false)
    private Player player;
    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id", nullable = false)
    private Team team;
    @Column(name = "minute", nullable = false)
    int minute;

    public Goal() {
    }

    public Goal(Match match, Player player, Team team, int minute) {
        this.match = match;
        this.player = player;
        this.team = team;
        this.minute = minute;
    }

    public long getGoalId() {
        return goalId;
    }

    public void setGoalId(long goalId) {
        this.goalId = goalId;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
