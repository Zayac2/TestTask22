package com.example.testtask.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team_id;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String patronymic;
    @Column(nullable = false)
    private String role;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    public Member() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Team id_team) {
        this.team_id = id_team;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
