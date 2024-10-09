package com.example.seriea_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String position;
    private int number;
    private String nationality;
    private int age;
    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;
}
/*

JSON TO ADD PLAYER
{
  "name" : "Daniel Carvajal",
  "position" : "Defender",
  "number" : 2,
  "nationality" : "Spain",
  "age" : 32,
  "teamName" : "Real Madrid"
}
 */
