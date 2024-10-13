package com.adrian.champlonshipfootball.model;

import jakarta.persistence.*;

@Entity
@Table(name = "season")
public class Season {
    @Id
    @GeneratedValue
    @Column(name = "season_id", nullable = false)
    private int seasonId;
    @Column(name = "season_name", nullable = false)
    private String seasonName;

    public Season() {}

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }
}
