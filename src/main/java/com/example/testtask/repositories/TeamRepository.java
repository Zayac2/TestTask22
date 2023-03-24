package com.example.testtask.repositories;

import com.example.testtask.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query(value = "SELECT * FROM team WHERE sport_type = :sport_type", nativeQuery = true)
    List<Team> findBySportType(String sport_type);

    @Query(value = "SELECT * FROM team WHERE date_found BETWEEN :date1 AND :date2", nativeQuery = true)
    List<Team> getForPeriod(Date date1, Date date2);
}
