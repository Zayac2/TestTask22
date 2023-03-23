package com.example.testtask.services;

import com.example.testtask.repositories.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamsService {
    private final TeamRepository teamRepository;

    public TeamsService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


}
