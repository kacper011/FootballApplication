package com.example.seriea_api.controller;

import com.example.seriea_api.model.TeamPoints;
import com.example.seriea_api.service.TeamPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seriea")
public class SerieAController {

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
        try {
            TeamPoints updatedTeam = teamPointsService.updatePoints(teamName, points);
            return String.format(
                    "Updated points for team: %s. New points: %d, Matches Played: %d, Wins: %d, Draws: %d, Losses: %d",
                    updatedTeam.getTeamName(),
                    updatedTeam.getPoints(),
                    updatedTeam.getMatchesPlayed(),
                    updatedTeam.getWins(),
                    updatedTeam.getDraws(),
                    updatedTeam.getLosses()
            );
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @GetMapping("/table")
    public List<TeamPoints> getLeagueTable() {
        return teamPointsService.getLeagueTable();
    }
}
