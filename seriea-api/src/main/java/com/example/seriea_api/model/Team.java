package com.example.seriea_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String stadium;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coach_id")
    @JsonManagedReference
    private Coach coach;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Player> players;
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