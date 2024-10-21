package com.example.bundesliga_api.service;

import com.example.bundesliga_api.dto.PlayerDTO;
import com.example.bundesliga_api.mapper.PlayerMapper;
import com.example.bundesliga_api.model.Player;
import com.example.bundesliga_api.model.Team;
import com.example.bundesliga_api.repository.PlayerRepository;
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

    public Player updatePlayer(Long id, PlayerDTO playerDTO) {

        Player existingPlayer = playerRepository.findById(id).orElse(null);

        if (existingPlayer == null) {
            return null;
        }

        existingPlayer.setName(playerDTO.getName());
        existingPlayer.setPosition(playerDTO.getPosition());
        existingPlayer.setNumber(playerDTO.getNumber());
        existingPlayer.setNationality(playerDTO.getNationality());
        existingPlayer.setAge(playerDTO.getAge());

        Team team = teamService.findByName(playerDTO.getTeamName());
        if (team != null) {
            existingPlayer.setTeam(team);
        }

        return playerRepository.save(existingPlayer);
    }
}

