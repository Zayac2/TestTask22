package com.example.testtask.model;

import com.example.testtask.enums.ERole;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ERole roles;

    public Role() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole getRoles() {
        return roles;
    }

    public void setRoles(ERole role) {
        this.roles = role;
    }
}
