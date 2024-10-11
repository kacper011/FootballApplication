package com.example.seriea_api.service;

import com.example.seriea_api.dto.PlayerDTO;
import com.example.seriea_api.mapper.PlayerMapper;
import com.example.seriea_api.model.Player;
import com.example.seriea_api.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamService teamService;

    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(PlayerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public List<Player> getPlayersByTeamId(Long teamId) {
        return playerRepository.findByTeamId(teamId);
    }

    public Player savePlayer(Player player) {
        if (player.getTeam() != null && player.getTeam().getId() != null) {
            player.setTeam(teamService.getTeamById(player.getTeam().getId()));
        }
        return playerRepository.save(player);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}