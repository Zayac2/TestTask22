package com.example.testtask.controllers;

import com.example.testtask.dto.*;
import com.example.testtask.enums.Function;
import com.example.testtask.model.*;
import com.example.testtask.services.TeamsService;
import liquibase.pro.packaged.P;
import liquibase.pro.packaged.R;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class TeamsController {
    private final TeamsService teamsService;

    public TeamsController(TeamsService teamsService) {
        this.teamsService = teamsService;
    }

    @GetMapping("/teams/all")
    public List<Team> teams() {
        return teamsService.findAllTeams();
    }

    @GetMapping("/teams/sport")
    public List<Team> teamsBySport(@RequestBody String sportType) {
        return teamsService.findTeamsBySport(sportType);
    }

    @GetMapping("/teams/period")
    public List<Team> teamsForPeriod(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
                                     @RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2) {
        return teamsService.findTeamsForPeriod(date1, date2);
    }

    @GetMapping("members/total")
    public Long countAllMembers() {
        return teamsService.countMembers();
    }

    @GetMapping("/teams/{fun}")
    public Long teamsFunction(@PathVariable(name = "fun") String function) {
        switch (Function.toFunction(function)) {
            case SUM:
                return teamsService.sumAllMembersAmongTeams();
            case MIN:
                return teamsService.minMembersAmongTeams();
            case MAX:
                return teamsService.maxMembersAmongTeams();
            case AVG:
                return teamsService.avgMembersAmongTeams();
            case COUNT:
                return teamsService.countTeams();
            default:
                return -1L;
        }
    }

    @GetMapping("/members/team")
    public List<Member> membersByTeamId(@RequestBody Long teamId) {
        return teamsService.findMembersByTeamId(teamId);
    }

    @GetMapping("/members/role")
    public List<Member> membersByRoleInTeam(@RequestBody Long teamId, @RequestBody String role) {
        return teamsService.findMembersByRoleInTeam(teamId, role);
    }

    @GetMapping("/competitions/sport")
    public List<Competition> competitionSort(@RequestBody String sportType) {
        return teamsService.findCompetitionsBySportType(sportType);
    }

    @GetMapping("/inventory/team")
    public List<Inventory> inventoryByTeamId(@RequestBody Long id) {
        return teamsService.findInventoryByTeamId(id);
    }

    @GetMapping("/employees/team")
    public List<Employee> employeesByTeamId(@RequestBody Long id) {
        return teamsService.findEmployeesByTeamId(id);
    }

    @PostMapping("/teams/new")
    public void addTeam(@RequestBody TeamRequest dto) {
        teamsService.addTeam(dto);
    }

    @PostMapping("/members/new")
    public ResponseEntity<String> addMember(@RequestBody MemberRequest dto) {
        try {
            teamsService.addMember(dto);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Age must be between 3 and 40 years old.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Member: " + dto.getName() + " created!", HttpStatus.CREATED);
    }

    @PostMapping("/competitions/new")
    public void addCompetition(@RequestBody CompetitionRequest dto) {
        teamsService.addCompetition(dto);
    }

    @PostMapping("/inventory/new")
    public void addInventory(@RequestBody InventoryRequest dto) {
        teamsService.addInventory(dto);
    }

    @PostMapping("/employees/new")
    public void addEmployee(@RequestBody EmployeeRequest dto) {
        teamsService.addEmployee(dto);
    }

    @PutMapping("/members/transfer")
    public void transferMember(@RequestBody Long id, @RequestBody Long teamId) {
        teamsService.transferMember(id, teamId);
    }

    @PutMapping("/members/update")
    public void updateMember(@RequestBody Long id, @RequestBody MemberRequest dto) {
        teamsService.updateMember(id, dto);
    }

    @PutMapping("/teams/update")
    public void updateTeam(@RequestBody Long id, @RequestBody TeamRequest dto) {
        teamsService.updateTeam(id, dto);
    }

    @PutMapping("/competitions/update")
    public void updateCompetition(@RequestBody Long id, @RequestBody CompetitionRequest dto) {
        teamsService.updateCompetition(id, dto);
    }

    @PutMapping("/inventory/update")
    public void updateInventory(@RequestBody Long id, @RequestBody InventoryRequest dto) {
        teamsService.updateInventory(id, dto);
    }

    @PutMapping("/employees/update")
    public void updateEmployee(@RequestBody Long id, @RequestBody EmployeeRequest dto) {
        teamsService.updateEmployee(id, dto);
    }

    @DeleteMapping("/members/delete")
    public void deleteMember(@RequestBody Long id) {
        teamsService.deleteMember(id);
    }

    @DeleteMapping("/teams/delete")
    public void deleteTeams(@RequestBody Long id) {
        teamsService.deleteTeam(id);
    }

    @DeleteMapping("/competitions/delete")
    public void deleteCompetition(@RequestBody Long id) {
        teamsService.deleteCompetition(id);
    }

    @DeleteMapping("/inventory/delete")
    public void deleteInventory(@RequestBody Long id) {
        teamsService.deleteInventory(id);
    }

    @DeleteMapping("/employees/delete")
    public void deleteEmployee(@RequestBody Long id) {
        teamsService.deleteEmployee(id);
    }
}
