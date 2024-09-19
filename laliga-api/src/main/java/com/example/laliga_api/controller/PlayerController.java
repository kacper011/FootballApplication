package com.example.laliga_api.controller;

import com.example.laliga_api.dto.PlayerDTO;
import com.example.laliga_api.model.Player;
import com.example.laliga_api.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laliga/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    @GetMapping("/team/{teamId}")
    public List<Player> getPlayersByTeamId(@PathVariable Long teamId) {
        return playerService.getPlayersByTeamId(teamId);
    }

    @PostMapping
    public Player savePlayer(@RequestBody Player player) {
        return playerService.savePlayer(player);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }
}
