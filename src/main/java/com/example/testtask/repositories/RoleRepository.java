package com.example.testtask.repositories;

import com.example.testtask.enums.ERole;
import com.example.testtask.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoles(ERole roles);
}
