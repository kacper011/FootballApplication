package com.example.bundesliga_api.dto;

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
    private String teamName;

    public PlayerDTO(){

    }

    public PlayerDTO(int id, String name, String position, int number, String nationality, int age) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.number = number;
        this.nationality = nationality;
        this.age = age;
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
