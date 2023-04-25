package com.example.testtask.repositories;

import com.example.testtask.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query(value = "SELECT * FROM inventory WHERE team_id = :teamId", nativeQuery = true)
    List<Inventory> findInventoryByTeamId(Long teamId);
}
