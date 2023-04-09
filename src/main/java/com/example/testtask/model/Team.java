package com.example.testtask.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @Column(name = "sport_type", nullable = false)
    private String sportType;

    @Column(name = "date_found", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFound;

    @Column(name = "total_members", nullable = false, columnDefinition = "bigint default 0")
    private Long totalMembers;

    public Team() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String team_name) {
        this.teamName = team_name;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sport_type) {
        this.sportType = sport_type;
    }

    public Date getDateFound() {
        return dateFound;
    }

    public void setDateFound(Date date_found) {
        this.dateFound = date_found;
    }

    public Long getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(Long totalMembers) {
        this.totalMembers = totalMembers;
    }
}
