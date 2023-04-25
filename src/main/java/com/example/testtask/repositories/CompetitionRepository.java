package com.example.testtask.repositories;

import com.example.testtask.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    @Query(value = "SELECT * FROM competition WHERE sportType = :sportType", nativeQuery = true)
    List<Competition> findAllBySportType(String sportType);
}
