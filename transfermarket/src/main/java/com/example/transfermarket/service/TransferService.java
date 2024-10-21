package com.example.transfermarket.service;

import com.example.transfermarket.dto.PlayerDTO;
import com.example.transfermarket.model.Player;
import com.example.transfermarket.model.Transfer;
import com.example.transfermarket.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public TransferService(TransferRepository transferRepository, RestTemplate restTemplate) {
        this.transferRepository = transferRepository;
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

    public Transfer transferPlayer(String league, int playerId, String fromTeam, String toTeam, double value) {
        String url = String.format("http://localhost:8080/%s/players/%d", league, playerId);
        Player player = restTemplate.getForObject(url, Player.class);

        if (player != null) {
            player.setTeamName(toTeam);

            restTemplate.put(url, player);


            Transfer transfer = new Transfer();
            transfer.setPlayerId(playerId);
            transfer.setFromTeam(fromTeam);
            transfer.setToTeam(toTeam);
            transfer.setValue(value);
            transfer.setTransferDate(LocalDateTime.now());
            return transferRepository.save(transfer);
        }
        return null;
    }
}
