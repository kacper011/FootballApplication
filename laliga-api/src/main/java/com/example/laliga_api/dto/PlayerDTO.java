package com.example.laliga_api.dto;

import com.example.laliga_api.model.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDTO {
    private int id;
    private String name;
    private String position;
    private int number;
    private String nationality;
    private int age;


    public PlayerDTO convertToDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setPosition(player.getPosition());
        dto.setNumber(player.getNumber());
        dto.setNationality(player.getNationality());
        dto.setAge(player.getAge());
        return dto;
    }
}
