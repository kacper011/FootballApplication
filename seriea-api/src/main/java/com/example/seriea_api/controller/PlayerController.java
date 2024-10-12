package com.example.seriea_api.controller;

import com.example.seriea_api.dto.PlayerDTO;
import com.example.seriea_api.mapper.PlayerMapper;
import com.example.seriea_api.model.Player;
import com.example.seriea_api.model.Team;
import com.example.seriea_api.service.PlayerService;
import com.example.seriea_api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Line;
import java.util.List;

@RestController
@RequestMapping("/seriea/players")
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

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }
}
