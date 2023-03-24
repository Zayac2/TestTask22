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
    public List<Team> teamsBySport(@RequestBody String sport_type) {
        return teamsService.findTeamsBySport(sport_type);
    }

    @GetMapping("/teams/period")
    public List<Team> teamsForPeriod(@RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,
                                     @RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2) {
        return teamsService.findTeamsForPeriod(date1, date2);
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

    @PostMapping("/members/transfer")
    public void transferMember(@RequestBody Long id, @RequestBody Long team_id) {
        teamsService.transferMember(id, team_id);
    }

    @PutMapping("/member/update")
    public void updateMember(@RequestBody Long id, @RequestBody(required = false) MemberRequest dto) {
        teamsService.updateMember(id, dto);
    }

    @PutMapping("/teams/update")
    public void updateTeam(@RequestBody Long id, @RequestBody(required = false) TeamRequest dto) {
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
