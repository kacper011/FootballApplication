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
    private String teamName;
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
