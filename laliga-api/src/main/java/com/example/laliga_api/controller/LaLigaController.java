package com.example.laliga_api.controller;

import com.example.laliga_api.model.Team;
import com.example.laliga_api.model.TeamPoints;
import com.example.laliga_api.service.TeamPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/laliga")
public class LaLigaController {

    @Autowired
    private TeamPointsService teamPointsService;

    @PostMapping("/addTeam")
    public String addTeam(@RequestBody TeamPoints teamPoints) {
        try {
            teamPointsService.addTeam(teamPoints);
            return "Added team: " + teamPoints.getTeamName();
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/updatePoints")
    public String updatePoints(@RequestParam String teamName, @RequestParam int points) {
        TeamPoints updatedTeam = teamPointsService.updatePoints(teamName, points);
        return "Updated points for team: " + updatedTeam.getTeamName() + ". New points: " + updatedTeam.getPoints() + ", Matches Played: " + updatedTeam.getMatchesPlayed();
    }

    @GetMapping("/table")
    public List<TeamPoints> getLeagueTable() {
        return teamPointsService.getLeagueTable();
    }
}
