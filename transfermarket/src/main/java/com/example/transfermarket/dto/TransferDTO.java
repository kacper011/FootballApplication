package com.example.transfermarket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferDTO {
    private String fromTeam;
    private String toTeam;
    private double value;
}