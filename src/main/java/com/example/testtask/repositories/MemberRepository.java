package com.example.testtask.repositories;

import com.example.testtask.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value = "SELECT * FROM member WHERE team_id = :team_id", nativeQuery = true)
    List<Member> findAllByTeamId(Long team_id);

    @Query(value = "SELECT * FROM member WHERE team_id = :team_id AND role = :role", nativeQuery = true)
    List<Member> findAllByRoleInTeam(Long team_id, String role);

    @Modifying
    @Query(value = "UPDATE member SET team_id = :team_id WHERE id = :id", nativeQuery = true)
    void transfer(Long id, Long team_id);
}
