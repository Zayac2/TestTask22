package com.example.testtask.services;

import com.example.testtask.dto.*;
import com.example.testtask.model.*;
import com.example.testtask.repositories.*;
import liquibase.pro.packaged.T;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static com.example.testtask.enums.AgeCategory.*;

@Service
@Transactional
public class TeamsService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final CompetitionRepository competitionRepository;
    private final InventoryRepository inventoryRepository;
    private final EmployeeRepository employeeRepository;

    public TeamsService(TeamRepository teamRepository,
                        MemberRepository memberRepository,
                        CompetitionRepository competitionRepository,
                        InventoryRepository inventoryRepository,
                        EmployeeRepository employeeRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
        this.competitionRepository = competitionRepository;
        this.inventoryRepository = inventoryRepository;
        this.employeeRepository = employeeRepository;
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

    public List<Competition> findCompetitionsBySportType(String sportType) {
        return competitionRepository.findAllBySportType(sportType);
    }

    public List<Inventory> findInventoryByTeamId(Long id) {
        return inventoryRepository.findInventoryByTeamId(id);
    }

    public List<Employee> findEmployeesByTeamId(Long id) {
        return employeeRepository.findEmployeesByTeamId(id);
    }

    public void addTeam(TeamRequest dto) {
        Team team = new Team();
        saveTeam(dto, team);
    }

    public void addMember(MemberRequest dto) {
        Member member = new Member();
        saveMember(dto, member);
        teamRepository.incrementMember(dto.getTeamId());
    }

    public void addCompetition(CompetitionRequest dto) {
        Competition competition = new Competition();
        saveCompetition(dto, competition);
    }

    public void addInventory(InventoryRequest dto) {
        Inventory inventory = new Inventory();
        saveInventory(dto, inventory);
    }

    public void addEmployee(EmployeeRequest dto) {
        Employee employee = new Employee();
        saveEmployee(dto, employee);
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

    public void updateCompetition(Long id, CompetitionRequest dto) {
        Competition updatedCompetition = competitionRepository.findById(id).get();
        saveCompetition(dto, updatedCompetition);
    }

    public void updateInventory(Long id, InventoryRequest dto) {
        Inventory updatedInventory = inventoryRepository.findById(id).get();
        saveInventory(dto, updatedInventory);
    }

    public void updateEmployee(Long id, EmployeeRequest dto) {
        Employee updatedEmployee = employeeRepository.findById(id).get();
        saveEmployee(dto, updatedEmployee);
    }

    public void saveMember(MemberRequest dto, Member member) {
        member.setTeamId(teamRepository.getReferenceById(dto.getTeamId()));
        member.setName(dto.getName());
        member.setSurname(dto.getSurname());
        member.setPatronymic(dto.getPatronymic());
        member.setRole(dto.getRole());
        member.setBirthday(dto.getBirthday());

        ageDistraction(member);

        memberRepository.save(member);
    }

    public void ageDistraction(Member member) {

        int age = Period.between(LocalDate.now(), member.getBirthday().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()).getYears();

        if (age < 3) {
            throw new RuntimeException();
        } else if (age >= 3 && age < 9) {
            member.setAgeCategory(CHILDREN);
        } else if (age >= 9 && age < 19) {
            member.setAgeCategory(TEENAGERS);
        } else if (age >= 19 && age < 22) {
            member.setAgeCategory(YOUTH);
        } else if (age >= 22 && age < 41) {
            member.setAgeCategory(ADULTS);
        } else {
            throw new RuntimeException();
        }
    }

    public void saveTeam(TeamRequest dto, Team team) {
        team.setTeamName(dto.getTeamName());
        team.setSportType(dto.getSportType());
        team.setDateFound(dto.getDateFound());
        team.setAddress(dto.getAddress());

        teamRepository.save(team);
    }

    public void saveCompetition(CompetitionRequest dto, Competition competition) {
        competition.setName(dto.getName());
        competition.setSportType(dto.getSportType());
        competition.setNumberOfTeams(dto.getNumberOfTeams());
        competition.setStart(dto.getStart());
        competition.setEnd(dto.getEnd());

        competitionRepository.save(competition);
    }

    public void saveInventory(InventoryRequest dto, Inventory inventory) {
        inventory.setTeamId(teamRepository.getReferenceById(dto.getTeamId()));
        inventory.setName(dto.getName());
        inventory.setAmount(dto.getAmount());

        inventoryRepository.save(inventory);
    }

    public void saveEmployee(EmployeeRequest dto, Employee employee) {
        employee.setTeamId(teamRepository.getReferenceById(dto.getTeamId()));
        employee.setName(dto.getName());
        employee.setSurname(dto.getSurname());
        employee.setPatronymic(dto.getPatronymic());
        employee.setPosition(dto.getPosition());
        employee.setSalary(dto.getSalary());
        employee.setBirthday(dto.getBirthday());
        employee.setStartDate(dto.getStartDate());

        employeeRepository.save(employee);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
        Long teamId = memberRepository.findById(id).get().getTeamId().getId();
        teamRepository.decrementMember(teamId);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    public void deleteCompetition(Long id) {
        competitionRepository.deleteById(id);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    private int[] bubbleSort(int[] arr) {
        boolean change = true;
        while (change) {
            change = false;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    change = true
                }
            }
        }

        return arr;
    }
}