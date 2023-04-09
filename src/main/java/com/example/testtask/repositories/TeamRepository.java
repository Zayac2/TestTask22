package com.example.testtask.repositories;

import com.example.testtask.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = "SELECT * FROM team WHERE sport_type = :sportType;", nativeQuery = true)
    List<Team> findBySportType(String sportType);

    @Query(value = "SELECT * FROM team WHERE date_found BETWEEN :date1 AND :date2;", nativeQuery = true)
    List<Team> getForPeriod(Date date1, Date date2);

    @Query(value = "SELECT COUNT(*) FROM team;", nativeQuery = true)
    Long countAllTeams();

    @Query(value = "SELECT MIN(total_members) FROM team;", nativeQuery = true)
    Long minMembers();

    @Query(value = "SELECT MAX(total_members) FROM team;", nativeQuery = true)
    Long maxMembers();

    @Query(value = "SELECT AVG(total_members) FROM team;", nativeQuery = true)
    Long avgMembers();

    @Query(value = "SELECT SUM(total_members) FROM team;", nativeQuery = true)
    Long sumMembers();

    @Modifying
    @Query(value = "UPDATE team SET total_members = total_members + 1 WHERE team_id = :teamId;",
            nativeQuery = true)
    void incrementMember(Long teamId);

    @Modifying
    @Query(value = "UPDATE team SET total_members = total_members - 1 WHERE team_id = :teamId;",
            nativeQuery = true)
    void decrementMember(Long teamId);
}
