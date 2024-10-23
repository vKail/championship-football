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
    public List<Player> getPlayers() throws Exception {
        try {
            return playerService.findAllPlayers();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @GetMapping("/players/{id}")
    public Player getPlayerById(@PathVariable Long id) throws Exception {
        try {
            return playerService.findPlayerById(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PostMapping("/players")
    public Player savePlayer(@RequestBody Player player) throws Exception {
        try {
            return playerService.savePlayer(player);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PutMapping("/players/{id}")
    public Player updatePlayer(@PathVariable Long id, @RequestBody Player player) throws Exception {
        try {
            return playerService.updatePlayer(id, player);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/players/{id}")
    public void deletePlayer(@PathVariable Long id) throws Exception {
        try {
            playerService.deletePlayer(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

}
