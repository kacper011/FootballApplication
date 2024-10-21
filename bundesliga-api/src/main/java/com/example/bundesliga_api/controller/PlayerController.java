package com.example.bundesliga_api.controller;

import com.example.bundesliga_api.dto.PlayerDTO;
import com.example.bundesliga_api.mapper.PlayerMapper;
import com.example.bundesliga_api.model.Player;
import com.example.bundesliga_api.model.Team;
import com.example.bundesliga_api.service.PlayerService;
import com.example.bundesliga_api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bundesliga/players")
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
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id) {

        Player player = playerService.getPlayerById(id);

        PlayerDTO playerDTO = playerMapper.toDTO(player);

        if (playerDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(playerDTO);
    }


    @GetMapping("/team/{teamId}")
    public List<Player> getPlayersByTeamId(@PathVariable Long teamId) {
        return playerService.getPlayersByTeamId(teamId);
    }

    @PostMapping
    public Player savePlayer(@RequestBody PlayerDTO playerDTO) {

        Team team = teamService.findByName(playerDTO.getTeamName());

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

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long id, @RequestBody PlayerDTO playerDTO) {
        Player player = playerService.updatePlayer(id, playerDTO);

        if (player == null) {
            return ResponseEntity.notFound().build();
        }

        PlayerDTO updatedPlayerDTO = playerMapper.toDTO(player);
        return ResponseEntity.ok(updatedPlayerDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }
}
