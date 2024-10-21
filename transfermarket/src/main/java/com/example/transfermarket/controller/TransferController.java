package com.example.transfermarket.controller;

import com.example.transfermarket.dto.PlayerDTO;
import com.example.transfermarket.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfermarket")
public class TransferController {

    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/{league}/players/{playerId}")
    public PlayerDTO getPlayer(@PathVariable String league, @PathVariable int playerId) {
        PlayerDTO playerDTO = transferService.getPlayerFromLeague(league, playerId);
        if (playerDTO == null) {
            throw new RuntimeException("Player not found with ID: " + playerId);
        }
        return playerDTO;
    }
}
