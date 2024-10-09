package com.example.seriea_api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "points")
public class TeamPoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String teamName;
    private int points;
    private int matchesPlayed;
    private int wins;
    private int draws;
    private int losses;
}