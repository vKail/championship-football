package com.adrian.champlonshipfootball.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    @OneToMany(mappedBy = "team")
    private Set<Player> player;
//    @OneToOne
//    @JoinColumn(name = "dt_id", referencedColumnName = "dt_id", nullable = false)
//    private Dt dt;
    @ManyToMany
    @JoinTable(
            name = "team_categories",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    public Team() {
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

    public Set<Player> getPlayer() {
        return player;
    }

    public void setPlayer(Set<Player> player) {
        this.player = player;
    }

//    public Dt getDt() {
//        return dt;
//    }
//
//    public void setDt(Dt dt) {
//        this.dt = dt;
//    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}