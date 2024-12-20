package com.example.laliga_api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerDTO {
    private int id;
    private String name;
    private String position;
    private int number;
    private String nationality;
    private int age;
    private String teamName;

    public PlayerDTO(int id, String name, String position, int number, String nationality, int age) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.number = number;
        this.nationality = nationality;
        this.age = age;
    }

    public PlayerDTO(int id, String name, String position, int number, String nationality, int age, String teamName) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.number = number;
        this.nationality = nationality;
        this.age = age;
        this.teamName = teamName;
    }
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
