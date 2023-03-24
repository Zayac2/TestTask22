package com.example.testtask.services;

import com.example.testtask.dto.MemberRequest;
import com.example.testtask.dto.TeamRequest;
import com.example.testtask.model.Member;
import com.example.testtask.model.Team;
import com.example.testtask.repositories.MemberRepository;
import com.example.testtask.repositories.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TeamsService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    public TeamsService(TeamRepository teamRepository, MemberRepository memberRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
    }

    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    public List<Team> findTeamsBySport(String sport_type) {
        return teamRepository.findBySportType(sport_type);
    }

    public List<Team> findTeamsForPeriod(Date date1, Date date2) {
        return teamRepository.getForPeriod(date1, date2);
    }

    public List<Member> findMembersByTeamId(Long team_id) {
        return memberRepository.findAllByTeamId(team_id);
    }

    public List<Member> findMembersByRoleInTeam(Long team_id, String role) {
        return memberRepository.findAllByRoleInTeam(team_id, role);
    }

    public void addTeam(TeamRequest dto) {
        Team team = new Team();
        team.setTeam_name(dto.getTeam_name());
        team.setSport_type(dto.getSport_type());
        team.setDate_found(dto.getDate_found());

        teamRepository.save(team);
    }

    public void addMember(MemberRequest dto) {
        Member member = new Member();
        member.setTeam_id(dto.getTeam_id());
        member.setName(dto.getName());
        member.setSurname(dto.getSurname());
        member.setPatronymic(dto.getPatronymic());
        member.setRole(dto.getRole());
        member.setBirthday(dto.getBirthday());

        memberRepository.save(member);
    }

    public void transferMember(Long id, Long team_id) {
        memberRepository.transfer(id, team_id);
    }

    public void updateMember(Long id, MemberRequest dto) {
        Member updatedMember = memberRepository.findById(id).get();
        if(dto.getTeam_id() != null) updatedMember.setTeam_id(dto.getTeam_id());
        if(dto.getName() != null) updatedMember.setName(dto.getName());
        if(dto.getSurname() != null) updatedMember.setSurname(dto.getSurname());
        if(dto.getPatronymic() != null) updatedMember.setPatronymic(dto.getPatronymic());
        if(dto.getRole() != null) updatedMember.setRole(dto.getRole());
        if(dto.getBirthday() != null) updatedMember.setBirthday(dto.getBirthday());

        memberRepository.save(updatedMember);
    }

    public void updateTeam(Long id, TeamRequest dto) {
        Team updatedTeam = teamRepository.findById(id).get();
        if(dto.getTeam_name() != null) updatedTeam.setTeam_name(dto.getTeam_name());
        if(dto.getSport_type() != null) updatedTeam.setSport_type(dto.getSport_type());
        if(dto.getDate_found() != null) updatedTeam.setDate_found(dto.getDate_found());

        teamRepository.save(updatedTeam);
    }
}