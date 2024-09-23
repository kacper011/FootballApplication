package com.example.bundesliga_api.mapper;

import com.example.bundesliga_api.dto.PlayerDTO;
import com.example.bundesliga_api.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    // Konwersja z encji Player na PlayerDTO
    public static PlayerDTO toDTO(Player player) {
        if (player == null) {
            return null;
        }

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        playerDTO.setPosition(player.getPosition());
        playerDTO.setNumber(player.getNumber());
        playerDTO.setNationality(player.getNationality());
        playerDTO.setAge(player.getAge());

        // Przypisanie nazwy drużyny
        if (player.getTeam() != null) {
            playerDTO.setTeamName(player.getTeam().getName());
        }
        return playerDTO;
    }

    // Konwersja z PlayerDTO na encję Player
    public Player toEntity(PlayerDTO dto) {
        if (dto == null) {
            return null;
        }

        Player player = new Player();
        player.setId(dto.getId());
        player.setName(dto.getName());
        player.setPosition(dto.getPosition());
        player.setNumber(dto.getNumber());
        player.setNationality(dto.getNationality());
        player.setAge(dto.getAge());

        return player;
    }
}
