package com.example.laliga_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    private String name;
    private String stadium;
    private Coach coach;
    private List<Player> players;
}
