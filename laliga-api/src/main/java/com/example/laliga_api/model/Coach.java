package com.example.laliga_api.model;

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
@Table(name = "coaches")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nationality;
    private int age;

    @OneToOne(mappedBy = "coach")
    @JsonBackReference
    private Team team;

    public Coach(Long id, String name, String nationality, int age) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.age = age;
    }
}
