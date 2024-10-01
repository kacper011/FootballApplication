package com.example.premierleague_api.controller;

import com.example.premierleague_api.model.Team;
import com.example.premierleague_api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Line;
import java.util.List;

@RestController
@RequestMapping("/premierleague/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable("id") Long id) {
        return teamService.getTeamById(id);
    }

    @GetMapping("/name/{name}")
    public Team getTeamByName(@PathVariable String name) {
        return teamService.findByName(name);
    }

    @PostMapping
    public Team saveTeam(@RequestBody Team team) {
        return teamService.saveTeam(team);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable("id") Long id) {
        teamService.deleteTeam(id);
    }
}
