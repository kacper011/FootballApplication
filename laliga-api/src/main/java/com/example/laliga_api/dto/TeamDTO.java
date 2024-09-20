package com.example.laliga_api.dto;

import com.example.laliga_api.model.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class TeamDTO {
    private Long id;
    private String name;
    private String stadium;
    private CoachDTO coach;
    private List<PlayerDTO> players;
}

/*
JSON TO ADD TEAM
{
  "name" : "Real Madrid",
  "stadium" : "Estadio Santiago Bernabéu",
  "coach" : {
    "name" : "Carlo Ancelotti",
    "nationality" : "Italy",
    "age" : 65
  }
}
 */
