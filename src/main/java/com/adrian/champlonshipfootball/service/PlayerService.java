package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Player;
import com.adrian.champlonshipfootball.model.Category;
import com.adrian.champlonshipfootball.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    public Player findPlayerById(long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(long id, Player player) {
        Player updatedPlayer = findPlayerById(id);
        updatedPlayer.setFirstname(player.getFirstname());
        updatedPlayer.setLastname(player.getLastname());
        updatedPlayer.setBib(player.getBib());
        updatedPlayer.setTeam(player.getTeam());
        updatedPlayer.setBirthdate(player.getBirthdate());
        return playerRepository.save(updatedPlayer);
    }

    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
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
