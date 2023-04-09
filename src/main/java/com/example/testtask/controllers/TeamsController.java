package com.example.testtask.controllers;

import com.example.testtask.dto.MemberRequest;
import com.example.testtask.dto.TeamRequest;
import com.example.testtask.model.Member;
import com.example.testtask.model.Team;
import com.example.testtask.services.TeamsService;
import liquibase.pro.packaged.R;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @GetMapping("/teams/{fun}")
    public Long teamsFunction(@PathVariable(name = "fun") String function) {
        switch (function) {
            case "count":
                return teamsService.countTeams();

            case "sum":
                return teamsService.sumAllMembersAmongTeams();

            case "min":
                return teamsService.minMembersAmongTeams();

            case "max":
                return teamsService.maxMembersAmongTeams();

            case "avg":
                return teamsService.avgMembersAmongTeams();

            default:
                return -1L;
        }
    }

    @GetMapping("/members/team")
    public List<Member> membersByTeamId(@RequestBody Long team_id) {
        return teamsService.findMembersByTeamId(team_id);
    }

    @GetMapping("/members/role")
    public List<Member> membersByRoleInTeam(@RequestBody Long team_id, @RequestBody String role) {
        return teamsService.findMembersByRoleInTeam(team_id, role);
    }

    @PostMapping("/teams/new")
    public void addTeam(@RequestBody TeamRequest dto) {
        teamsService.addTeam(dto);
    }

    @PostMapping("/members/new")
    public void addMember(@RequestBody MemberRequest dto) {
        teamsService.addMember(dto);
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

    @DeleteMapping("/members/delete")
    public void deleteMember(@RequestBody Long id) {
        teamsService.deleteMember(id);
    }

    @DeleteMapping("/teams/delete")
    public void deleteTeams(@RequestBody Long id) {
        teamsService.deleteTeam(id);
    }
}
