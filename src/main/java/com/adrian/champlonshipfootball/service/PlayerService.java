package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Player;
import com.adrian.champlonshipfootball.repository.PlayerRepository;
import java.util.List;

public class PlayerService {
    PlayerRepository playerRepository;
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
        return playerRepository.save(player);
    }
    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
    }
}
