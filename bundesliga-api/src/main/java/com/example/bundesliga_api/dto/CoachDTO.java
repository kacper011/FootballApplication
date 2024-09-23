package com.example.bundesliga_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoachDTO {

    private Long id;
    private String name;
    private String nationality;
    private int age;
}
