package com.example.laliga_api.controller;

import com.example.laliga_api.model.Team;
import com.example.laliga_api.model.TeamPoints;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/laliga")
public class LaLigaController {

    private List<TeamPoints> leagueTable = new ArrayList<>();

    @PostMapping("/addTeam")
    public String addTeam(@RequestBody TeamPoints teamPoints) {
        leagueTable.add(teamPoints);
        return "Added team: " + teamPoints.getTeamName();
    }

    @PostMapping("/updatePoints")
    public String updatePoints(@RequestParam String teamName, @RequestParam int points) {

        for (TeamPoints team : leagueTable) {
            if (team.getTeamName().equalsIgnoreCase(teamName)) {
                team.setPoints(team.getPoints() + points);
                return "Updated points for team: " + teamName + ", New points: " + team.getPoints();
            }
        }
        return "Team not found" + teamName;
    }

    @GetMapping("/table")
    public List<TeamPoints> getLeagueTable() {
        return leagueTable;
    }
}
