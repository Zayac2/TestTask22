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

    public List<Team> findTeamsBySport(String sportType) {
        return teamRepository.findBySportType(sportType);
    }

    public List<Team> findTeamsForPeriod(Date date1, Date date2) {
        return teamRepository.getForPeriod(date1, date2);
    }

    public Long countTeams() {
        return teamRepository.countAllTeams();
    }

    public Long sumAllMembersAmongTeams() {
        return teamRepository.sumMembers();
    }

    public Long minMembersAmongTeams() {
        return teamRepository.minMembers();
    }

    public Long maxMembersAmongTeams() {
        return teamRepository.maxMembers();
    }

    public Long avgMembersAmongTeams() {
        return teamRepository.avgMembers();
    }

    public List<Member> findMembersByTeamId(Long teamId) {
        return memberRepository.findAllByTeamId(teamId);
    }

    public List<Member> findMembersByRoleInTeam(Long teamId, String role) {
        return memberRepository.findAllByRoleInTeam(teamId, role);
    }

    public Long countMembers() {
        return memberRepository.countAllMembers();
    }

    public void addTeam(TeamRequest dto) {
        Team team = new Team();
        saveTeam(dto, team);
    }

    public void addMember(MemberRequest dto) {
        Member member = new Member();
        saveMember(dto, member);
    }

    public void transferMember(Long id, Long teamId) {
        memberRepository.transfer(id, teamId);
    }

    public void updateMember(Long id, MemberRequest dto) {
        Member updatedMember = memberRepository.findById(id).get();
        saveMember(dto, updatedMember);
    }

    public void updateTeam(Long id, TeamRequest dto) {
        Team updatedTeam = teamRepository.findById(id).get();
        saveTeam(dto, updatedTeam);
    }
    public void saveMember(MemberRequest dto, Member member) {
        member.setTeamId(teamRepository.getReferenceById(dto.getTeamId()));
        member.setName(dto.getName());
        member.setSurname(dto.getSurname());
        member.setPatronymic(dto.getPatronymic());
        member.setRole(dto.getRole());
        member.setBirthday(dto.getBirthday());

        memberRepository.save(member);
        teamRepository.incrementMember(dto.getTeamId());
    }

    public void saveTeam(TeamRequest dto, Team team) {
        team.setTeamName(dto.getTeamName());
        team.setSportType(dto.getSportType());
        team.setDateFound(dto.getDateFound());

        teamRepository.save(team);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
        Long teamId = memberRepository.findById(id).get().getTeamId().getId();
        teamRepository.decrementMember(teamId);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}