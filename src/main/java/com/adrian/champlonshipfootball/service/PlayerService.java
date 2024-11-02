package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.dtos.PlayerDto;
import com.adrian.champlonshipfootball.model.Player;
import com.adrian.champlonshipfootball.model.Category;
import com.adrian.champlonshipfootball.model.Team;
import com.adrian.champlonshipfootball.repository.CategoryRepository;
import com.adrian.champlonshipfootball.repository.PlayerRepository;
import com.adrian.champlonshipfootball.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final CategoryRepository categoryRepository;

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository, CategoryRepository categoryRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<PlayerDto> findAllPlayers() {
        return playerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PlayerDto findPlayerById(long id) {
        return playerRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public PlayerDto savePlayer(PlayerDto playerDTO) {
        Player player = convertToEntity(playerDTO);
        if (!isPlayerEligibleForCategory(player, player.getCategory())) {
            throw new RuntimeException("Player is not eligible for the team's category");
        }
        Player savedPlayer = playerRepository.save(player);
        return convertToDTO(savedPlayer);
    }

    public PlayerDto updatePlayer(long id, PlayerDto playerDTO) {
        Player existingPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        updatePlayerFromDTO(existingPlayer, playerDTO);
        Player updatedPlayer = playerRepository.save(existingPlayer);
        return convertToDTO(updatedPlayer);
    }

    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
    }

    // Métodos de conversión privados
    private PlayerDto convertToDTO(Player player) {
        PlayerDto dto = new PlayerDto();
        dto.setPlayerId(player.getPlayerId());
        dto.setDni(player.getDni());
        dto.setFirstname(player.getFirstname());
        dto.setLastname(player.getLastname());
        dto.setBirthdate(player.getBirthdate());
        dto.setBib(player.getBib());
        if (player.getTeam() != null) {
            dto.setTeamId(player.getTeam().getTeamId());
            dto.setTeamName(player.getTeam().getName());
        }
        if (player.getCategory() != null) {
            dto.setCategoryId(player.getCategory().getCategoryId());
            dto.setCategoryName(player.getCategory().getCategoryName());
        }
        return dto;
    }

        private Player convertToEntity(PlayerDto dto) {
        Player player = new Player();
        player.setPlayerId(dto.getPlayerId());
        player.setDni(dto.getDni());
        player.setFirstname(dto.getFirstname());
        player.setLastname(dto.getLastname());
        player.setBirthdate(dto.getBirthdate());
        player.setBib(dto.getBib());

        if (dto.getTeamId() != null) {
            Team team = teamRepository.findById(dto.getTeamId())
                    .orElseThrow(() -> new RuntimeException("Team not found"));
            player.setTeam(team);
        }
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            player.setCategory(category);
        }
        return player;
    }

    private void updatePlayerFromDTO(Player player, PlayerDto dto) {
        player.setDni(dto.getDni());
        player.setFirstname(dto.getFirstname());
        player.setLastname(dto.getLastname());
        player.setBirthdate(dto.getBirthdate());
        player.setBib(dto.getBib());

        if (dto.getTeamId() != null) {
            Team team = teamRepository.findById(dto.getTeamId())
                    .orElseThrow(() -> new RuntimeException("Team not found"));
            player.setTeam(team);
        }
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            player.setCategory(category);
        }
    }

    public boolean isPlayerEligibleForCategory(Player player, Category category) {
        if (player.getBirthdate() == null) {
            return false;
        }

        int age = calculateAge(player.getBirthdate());
        return age >= category.getAgeMin() && age <= category.getAgeMax();
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
