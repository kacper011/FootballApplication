package com.example.laliga_api.mapper;

import com.example.laliga_api.dto.PlayerDTO;
import com.example.laliga_api.model.Player;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    // Konwersja z encji Player na PlayerDTO
    public PlayerDTO toDTO(Player player) {
        if (player == null) {
            return null;
        }

        PlayerDTO dto = new PlayerDTO();
        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setPosition(player.getPosition());
        dto.setNumber(player.getNumber());
        dto.setNationality(player.getNationality());
        dto.setAge(player.getAge());

        return dto;
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
