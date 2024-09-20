package com.example.laliga_api.service;

import com.example.laliga_api.model.TeamPoints;
import com.example.laliga_api.repository.TeamPointsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamPointsService {

    @Autowired
    private TeamPointsRepository teamPointsRepository;


    public TeamPoints addTeam(TeamPoints teamPoints) {

        List<TeamPoints> existingTeams = teamPointsRepository.findAllByTeamName(teamPoints.getTeamName());
        if (!existingTeams.isEmpty()) {
            throw new IllegalArgumentException("Team with name " + teamPoints.getTeamName() + " already exists.");
        }
        return teamPointsRepository.save(teamPoints);
    }


    @Transactional
    public TeamPoints updatePoints(String teamName, int points) {
        TeamPoints team = teamPointsRepository.findByTeamName(teamName);
        if (team == null) {
            throw new RuntimeException("Team not found with name: " + teamName);
        }
        team.setPoints(team.getPoints() + points);
        team.setMatchesPlayed(team.getMatchesPlayed() + 1);
        return teamPointsRepository.save(team);
    }

    public List<TeamPoints> getLeagueTable() {
        return teamPointsRepository.findAll();
    }
}
