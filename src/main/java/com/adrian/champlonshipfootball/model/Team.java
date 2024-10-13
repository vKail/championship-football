package com.adrian.champlonshipfootball.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Team")
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private long teamId;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Player> player;
    @OneToOne
    @JoinColumn(name = "dt_id", referencedColumnName = "dt_id", nullable = false)
    private Dt dt;
    @ManyToMany
    @JoinTable(
            name = "team_categories",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Team() {
    }

    public Team(long teamId, String name, List<Player> player, Dt dt, Set<Category> categories) {
        this.teamId = teamId;
        this.name = name;
        this.player = player;
        this.dt = dt;
        this.categories = categories;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayer() {
        return player;
    }

    public void setPlayer(List<Player> player) {
        this.player = player;
    }

    public Dt getDt() {
        return dt;
    }

    public void setDt(Dt dt) {
        this.dt = dt;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}