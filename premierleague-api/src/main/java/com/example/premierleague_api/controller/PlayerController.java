package com.example.premierleague_api.controller;

import com.example.premierleague_api.dto.PlayerDTO;
import com.example.premierleague_api.mapper.PlayerMapper;
import com.example.premierleague_api.model.Player;
import com.example.premierleague_api.model.Team;
import com.example.premierleague_api.service.PlayerService;
import com.example.premierleague_api.service.TeamService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/premierleague/players")
public class PlayerController {

    @Autowired
    private final PlayerService playerService;
    @Autowired
    private final TeamService teamService;
    @Autowired
    private final PlayerMapper playerMapper;
    @Autowired
    public PlayerController(PlayerService playerService, TeamService teamService, PlayerMapper playerMapper) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.playerMapper = playerMapper;
    }

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable("id") Long id) {

        Player player = playerService.getPlayerById(id);

        PlayerDTO playerDTO = playerMapper.toDTO(player);

        if (playerDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(playerDTO);
    }

    @GetMapping("/team/{teamId}")
    public List<Player> getPlayersByTeamId(@PathVariable("id") Long id) {
        return playerService.getPlayersByTeamId(id);
    }

    @PostMapping
    public Player savePlayer(@RequestBody PlayerDTO playerDTO) {

        Team team = teamService.findByName(playerDTO.getName());

        if (team == null) {
            throw new RuntimeException("Team not found with name: " + playerDTO.getTeamName());
        }

        Player player = new Player();
        player.setName(playerDTO.getName());
        player.setPosition(playerDTO.getPosition());
        player.setNumber(playerDTO.getNumber());
        player.setNationality(playerDTO.getNationality());
        player.setAge(playerDTO.getAge());
        player.setTeam(team);

        return playerService.savePlayer(player);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable("id") Long id) {
        playerService.deletePlayer(id);
    }
}