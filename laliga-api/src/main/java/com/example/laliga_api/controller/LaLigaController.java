package com.example.laliga_api.controller;

import com.example.laliga_api.model.Team;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/laliga")
public class LaLigaController {

    @GetMapping("/teams")
    public List<Team> getAllTeams() {
        return null;
    }
}
