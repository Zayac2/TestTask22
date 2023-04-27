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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/base")
public class TeamsController {
    private final TeamsService teamsService;

    public TeamsController(TeamsService teamsService) {
        this.teamsService = teamsService;
    }

    @GetMapping("/teams/all")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    public List<Team> teams() {
        return teamsService.findAllTeams();
    }

    @GetMapping("/teams/sport")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    public List<Team> teamsBySport(@RequestBody String sportType) {
        return teamsService.findTeamsBySport(sportType);
    }

    @GetMapping("/teams/period")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    public List<Team> teamsForPeriod(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
                                     @RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2) {
        return teamsService.findTeamsForPeriod(date1, date2);
    }

    @GetMapping("/members/total")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    public Long countAllMembers() {
        return teamsService.countMembers();
    }

    @GetMapping("/teams/{fun}")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    public List<Member> membersByTeamId(@RequestBody Long teamId) {
        return teamsService.findMembersByTeamId(teamId);
    }

    @GetMapping("/members/role")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    public List<Member> membersByRoleInTeam(@RequestBody Long teamId, @RequestBody String role) {
        return teamsService.findMembersByRoleInTeam(teamId, role);
    }

    @GetMapping("/competitions/sport")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public List<Competition> competitionSort(@RequestBody String sportType) {
        return teamsService.findCompetitionsBySportType(sportType);
    }

    @GetMapping("/inventory/team")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public List<Inventory> inventoryByTeamId(@RequestBody Long id) {
        return teamsService.findInventoryByTeamId(id);
    }

    @GetMapping("/employees/team")
    @PreAuthorize("hasRole('DIRECTOR') or hasRole('ADMIN')")
    public List<Employee> employeesByTeamId(@RequestBody Long id) {
        return teamsService.findEmployeesByTeamId(id);
    }

    @PostMapping("/teams/new")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    public void addTeam(@RequestBody TeamRequest dto) {
        teamsService.addTeam(dto);
    }

    @PostMapping("/members/new")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    public ResponseEntity<String> addMember(@RequestBody MemberRequest dto) {
        try {
            teamsService.addMember(dto);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Age must be between 3 and 40 years old.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Member: " + dto.getName() + " created!", HttpStatus.CREATED);
    }

    @PostMapping("/competitions/new")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public void addCompetition(@RequestBody CompetitionRequest dto) {
        teamsService.addCompetition(dto);
    }

    @PostMapping("/inventory/new")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public void addInventory(@RequestBody InventoryRequest dto) {
        teamsService.addInventory(dto);
    }

    @PostMapping("/employees/new")
    @PreAuthorize("hasRole('DIRECTOR') or hasRole('ADMIN')")
    public void addEmployee(@RequestBody EmployeeRequest dto) {
        teamsService.addEmployee(dto);
    }

    @PutMapping("/members/transfer")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    public void transferMember(@RequestBody Long id, @RequestBody Long teamId) {
        teamsService.transferMember(id, teamId);
    }

    @PutMapping("/members/update")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    public void updateMember(@RequestBody Long id, @RequestBody MemberRequest dto) {
        teamsService.updateMember(id, dto);
    }

    @PutMapping("/teams/update")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    public void updateTeam(@RequestBody Long id, @RequestBody TeamRequest dto) {
        teamsService.updateTeam(id, dto);
    }

    @PutMapping("/competitions/update")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public void updateCompetition(@RequestBody Long id, @RequestBody CompetitionRequest dto) {
        teamsService.updateCompetition(id, dto);
    }

    @PutMapping("/inventory/update")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public void updateInventory(@RequestBody Long id, @RequestBody InventoryRequest dto) {
        teamsService.updateInventory(id, dto);
    }

    @PutMapping("/employees/update")
    @PreAuthorize("hasRole('DIRECTOR') or hasRole('ADMIN')")
    public void updateEmployee(@RequestBody Long id, @RequestBody EmployeeRequest dto) {
        teamsService.updateEmployee(id, dto);
    }

    @DeleteMapping("/members/delete")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    public void deleteMember(@RequestBody Long id) {
        teamsService.deleteMember(id);
    }

    @DeleteMapping("/teams/delete")
    @PreAuthorize("hasRole('TRAINER') or hasRole('ADMIN')")
    public void deleteTeams(@RequestBody Long id) {
        teamsService.deleteTeam(id);
    }

    @DeleteMapping("/competitions/delete")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public void deleteCompetition(@RequestBody Long id) {
        teamsService.deleteCompetition(id);
    }

    @DeleteMapping("/inventory/delete")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public void deleteInventory(@RequestBody Long id) {
        teamsService.deleteInventory(id);
    }

    @DeleteMapping("/employees/delete")
    @PreAuthorize("hasRole('DIRECTOR') or hasRole('ADMIN')")
    public void deleteEmployee(@RequestBody Long id) {
        teamsService.deleteEmployee(id);
    }
}
