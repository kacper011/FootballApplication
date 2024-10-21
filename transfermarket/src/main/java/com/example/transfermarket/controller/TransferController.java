package com.example.transfermarket.controller;

import com.example.transfermarket.dto.PlayerDTO;
import com.example.transfermarket.dto.TransferDTO;
import com.example.transfermarket.model.Transfer;
import com.example.transfermarket.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{league}/players/{playerId}")
    public ResponseEntity<Transfer> transferPlayer(@PathVariable String league, @PathVariable int playerId, @RequestBody TransferDTO transferDTO) {
        Transfer transfer = transferService.transferPlayer(
                league,
                playerId,
                transferDTO.getFromTeam(),
                transferDTO.getToTeam(),
                transferDTO.getValue()
        );

        if (transfer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transfer);
    }
}
