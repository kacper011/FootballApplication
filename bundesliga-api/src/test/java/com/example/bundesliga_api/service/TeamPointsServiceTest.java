package com.example.bundesliga_api.service;

import com.example.bundesliga_api.model.Team;
import com.example.bundesliga_api.model.TeamPoints;
import com.example.bundesliga_api.repository.TeamPointsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

class TeamPointsServiceTest {


    @Mock
    private TeamPointsRepository teamPointsRepository;

    @InjectMocks
    private TeamPointsService teamPointsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @DisplayName("Add Team When Team Does Not Exist")
    @Test
    public void testAddTeamWhenTeamDoesNotExist() {

        //Given
        TeamPoints teamPoints = new TeamPoints(1L, "Team A", 9, 3, 3, 0, 0);

        when(teamPointsRepository.findAllByTeamName("Team A")).thenReturn(Collections.emptyList());
        when(teamPointsRepository.save(teamPoints)).thenReturn(teamPoints);

        //When
        TeamPoints result = teamPointsService.addTeam(teamPoints);

        //Then
        assertNotNull(result);
        assertEquals("Team A", result.getTeamName());

        verify(teamPointsRepository, times(1)).findAllByTeamName("Team A");
        verify(teamPointsRepository, times(1)).save(teamPoints);

    }

    @DisplayName("Add Team When Team Already Exists")
    @Test
    public void testAddTeamWhenTeamAlreadyExists() {

        //Given
        TeamPoints teamPoints = new TeamPoints(1L, "Team A", 9, 3, 3, 0, 0);
        List<TeamPoints> existingTeams = List.of(new TeamPoints(2L, "Team A", 12, 4, 4, 0, 0));

        when(teamPointsRepository.findAllByTeamName("Team A")).thenReturn(existingTeams);

        //When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            teamPointsService.addTeam(teamPoints);
        });

        assertEquals("Team with name Team A already exists.", exception.getMessage());

        verify(teamPointsRepository, times(1)).findAllByTeamName("Team A");
        verify(teamPointsRepository, times(0)).save(any(TeamPoints.class));
    }

    @DisplayName("Update Points When Team Exists And Points Are Valid")
    @Test
    public void testUpdatePointsWhenTeamExistsAndPointsAreValid() {

        //Given
        String teamName = "Team A";
        int pointsToAdd = 3;
        TeamPoints existingTeam = new TeamPoints(1L, "Team A", 9, 3, 3, 0, 0);

        when(teamPointsRepository.findByTeamName(teamName)).thenReturn(existingTeam);
        when(teamPointsRepository.save(existingTeam)).thenReturn(existingTeam);

        //When
        TeamPoints updatedTeam = teamPointsService.updatePoints(teamName, pointsToAdd);

        //Then
        assertNotNull(updatedTeam);
        assertEquals(12, updatedTeam.getPoints());
        assertEquals(4, updatedTeam.getMatchesPlayed());
        assertEquals(4, updatedTeam.getWins());
        assertEquals(0, updatedTeam.getDraws());
        assertEquals(0, updatedTeam.getLosses());

        verify(teamPointsRepository, times(1)).findByTeamName(teamName);
        verify(teamPointsRepository, times(1)).save(existingTeam);
    }

    @DisplayName("Update Points When Team Does Not Exist")
    @Test
    public void testUpdatePointsWhenTeamDoesNotExist() {

        //Given
        String teamName = "Nonexistent Team";
        int poinstToAdd = 3;

        when(teamPointsRepository.findByTeamName(teamName)).thenReturn(null);

        //When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            teamPointsService.updatePoints(teamName, poinstToAdd);
        });

        assertEquals("Team not found with name: " + teamName, exception.getMessage());

        verify(teamPointsRepository, times(1)).findByTeamName(teamName);
        verify(teamPointsRepository, times(0)).save(any());
    }

    @DisplayName("Update Points When Points Are Valid")
    @Test
    public void testUpdatePointsWhenPointsAreValid() {

        //Given
        String teamName = "Team A";
        int invalidPoints = 5;
        TeamPoints existingTeam = new TeamPoints(1L, "Team A", 9, 3, 3, 0, 0);

        when(teamPointsRepository.findByTeamName(teamName)).thenReturn(existingTeam);

        //When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            teamPointsService.updatePoints(teamName, invalidPoints);
        });

        assertEquals("Points must be 0, 1, or 3.", exception.getMessage());

        verify(teamPointsRepository, times(1)).findByTeamName(teamName);
        verify(teamPointsRepository, times(0)).save(any());
    }

    @DisplayName("Get League Table")
    @Test
    public void testGetLeagueTable() {

        //Given
        TeamPoints teamA = new TeamPoints(1L, "Team A", 4, 4, 1, 1, 2);
        TeamPoints teamB = new TeamPoints(2L, "Team B", 10, 4, 3, 1, 0);
        TeamPoints teamC = new TeamPoints(3L, "Team C", 6, 4, 1, 3, 0);

        List<TeamPoints> unsortedTeams = List.of(teamA, teamB, teamC);

        when(teamPointsRepository.findAll()).thenReturn(unsortedTeams);

        //When
        List<TeamPoints> leagueTable = teamPointsService.getLeagueTable();

        //Then
        assertNotNull(leagueTable);
        assertEquals(3, leagueTable.size());
        assertEquals("Team B", leagueTable.get(0).getTeamName());
        assertEquals("Team C", leagueTable.get(1).getTeamName());
        assertEquals("Team A", leagueTable.get(2).getTeamName());

        verify(teamPointsRepository, times(1)).findAll();
    }

}