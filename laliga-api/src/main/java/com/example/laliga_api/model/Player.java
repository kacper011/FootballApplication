package com.example.laliga_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private int id;
    private String name;
    private String position;
    private int number;
    private String nationality;
    private int age;
}
