package com.example.testtask.dto;

import javax.persistence.Column;
import java.util.Date;

public class TeamRequest {
    private String team_name;
    private String sport_type;
    private Date date_found;

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getSport_type() {
        return sport_type;
    }

    public void setSport_type(String sport_type) {
        this.sport_type = sport_type;
    }

    public Date getDate_found() {
        return date_found;
    }

    public void setDate_found(Date date_found) {
        this.date_found = date_found;
    }
}
