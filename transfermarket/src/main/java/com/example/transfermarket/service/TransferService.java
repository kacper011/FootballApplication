package com.example.transfermarket.service;

import com.example.transfermarket.dto.PlayerDTO;
import com.example.transfermarket.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransferService {

    private final RestTemplate restTemplate;

    @Autowired
    public TransferService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PlayerDTO getPlayerFromLeague(String league, int playerId) {

        String url = String.format("http://localhost:8080/%s/players/%d", league, playerId);
        Player player = restTemplate.getForObject(url, Player.class);

        if (player != null) {
            PlayerDTO playerDTO = new PlayerDTO();
            playerDTO.setId(player.getId());
            playerDTO.setName(player.getName());
            playerDTO.setPosition(player.getPosition());
            playerDTO.setNumber(player.getNumber());
            playerDTO.setNationality(player.getNationality());
            playerDTO.setAge(player.getAge());
            playerDTO.setTeamName(player.getTeamName());
            return playerDTO;
        }
        return null;
    }
}
