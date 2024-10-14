package com.adrian.champlonshipfootball.controller;

import com.adrian.champlonshipfootball.model.Player;
import com.adrian.champlonshipfootball.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public List<Player> getPlayers() {
        return playerService.findAllPlayers();
    }

    @GetMapping("/players/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return playerService.findPlayerById(id);
    }

    @PostMapping("/players")
    public Player savePlayer(@RequestBody Player player) {
        return playerService.savePlayer(player);
    }

    @PutMapping("/players/{id}")
    public Player updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        return playerService.updatePlayer(id, player);
    }

    @DeleteMapping("/players/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }

}
