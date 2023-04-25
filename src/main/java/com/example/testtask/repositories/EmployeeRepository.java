package com.example.testtask.repositories;

import com.example.testtask.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employee WHERE team_id = :teamId", nativeQuery = true)
    List<Employee> findEmployeesByTeamId(Long teamId);
}
