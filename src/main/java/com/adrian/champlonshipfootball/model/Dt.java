package com.adrian.champlonshipfootball.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Dt")
public class Dt {

    @Id
    @GeneratedValue
    @Column(name = "dt_id", nullable = false)
    private long dtId;
    @Column(name = "dni", nullable = false)
    private String dni;
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @OneToOne
    @JoinColumn(name = "team_id",referencedColumnName = "team_id", nullable = false)
    private Team team;

    public Dt() {}

    public Dt(long dtId, String dni, String firstname, String lastname, Team team) {
        this.dtId = dtId;
        this.dni = dni;
        this.firstname = firstname;
        this.lastname = lastname;
        this.team = team;
    }

    public long getDtId() {
        return dtId;
    }

    public void setDtId(long id) {
        this.dtId = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
