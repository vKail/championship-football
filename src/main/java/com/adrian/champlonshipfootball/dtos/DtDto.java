package com.adrian.champlonshipfootball.dtos;

import java.time.LocalDate;

public class DtDto {
    private long dtId;
    private String dni;
    private String firstname;
    private String lastname;
    private Long teamId;
    private String teamName;

    public DtDto() {
    }

    public long getDtId() {
        return dtId;
    }

    public void setDtId(long dtId) {
        this.dtId = dtId;
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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
