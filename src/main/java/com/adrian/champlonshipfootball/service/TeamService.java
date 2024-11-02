package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.dtos.TeamDto;
import com.adrian.champlonshipfootball.model.Category;
import com.adrian.champlonshipfootball.model.Player;
import com.adrian.champlonshipfootball.model.Team;
import com.adrian.champlonshipfootball.repository.CategoryRepository;
import com.adrian.champlonshipfootball.repository.TeamRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final CategoryRepository categoryRepository;

    public TeamService(TeamRepository teamRepository, CategoryRepository categoryRepository) {
        this.teamRepository = teamRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<TeamDto> findAllTeams() {
        return teamRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TeamDto findTeamById(long id) {
        return teamRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public TeamDto saveTeam(TeamDto teamDTO) {
        Team team = convertToEntity(teamDTO);
        Team savedTeam = teamRepository.save(team);
        return convertToDTO(savedTeam);
    }

    public TeamDto updateTeam(long id, TeamDto teamDTO) {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        updateTeamFromDTO(existingTeam, teamDTO);
        Team updatedTeam = teamRepository.save(existingTeam);
        return convertToDTO(updatedTeam);
    }

    public void deleteTeam(long id) {
        teamRepository.deleteById(id);
    }

    private TeamDto convertToDTO(Team team) {
        TeamDto dto = new TeamDto();
        dto.setTeamId(team.getTeamId());
        dto.setName(team.getName());

        if (team.getPlayer() != null) {
            dto.setPlayerIds(team.getPlayer().stream()
                    .map(player -> ((Player) player).getPlayerId())
                    .collect(Collectors.toSet()));
        }

        if (team.getCategories() != null) {
            dto.setCategoryIds(team.getCategories().stream()
                    .map(category -> ((Category) category).getCategoryId())
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    private Team convertToEntity(TeamDto dto) {
        Team team = new Team();
        team.setTeamId(dto.getTeamId());
        team.setName(dto.getName());

        if (dto.getCategoryIds() != null) {
            Set<Category> categories = dto.getCategoryIds().stream()
                    .map(id -> categoryRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Category not found")))
                    .collect(Collectors.toSet());
            team.setCategories(categories);
        }
        return team;
    }

    private void updateTeamFromDTO(Team team, TeamDto dto) {
        team.setName(dto.getName());
        if (dto.getCategoryIds() != null) {
            Set<Category> categories = dto.getCategoryIds().stream()
                    .map(id -> categoryRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Category not found")))
                    .collect(Collectors.toSet());
            team.setCategories(categories);
        }
    }
}
