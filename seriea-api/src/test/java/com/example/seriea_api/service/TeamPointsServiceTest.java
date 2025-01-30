package com.example.seriea_api.service;

import com.example.seriea_api.model.TeamPoints;
import com.example.seriea_api.repository.TeamPointsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class TeamPointsServiceTest {

    @Mock
    private TeamPointsRepository teamPointsRepository;

    @InjectMocks
    private TeamPointsService teamPointsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Add Team Success")
    @Test
    public void testAddTeamSuccess() {

        //Given
        TeamPoints teamPoints = new TeamPoints();
        teamPoints.setTeamName("Team A");

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

    @DisplayName("Add Team Team Already Exists")
    @Test
    public void testAddTeamTeamAlreadyExists() {

        //Given
        TeamPoints teamPoints = new TeamPoints();
        teamPoints.setTeamName("Team A");

        List<TeamPoints> existingTeams = List.of(teamPoints);
        when(teamPointsRepository.findAllByTeamName("Team A")).thenReturn(existingTeams);

        //When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> teamPointsService.addTeam(teamPoints));

        assertEquals("Team with name Team A already exists.", exception.getMessage());

        verify(teamPointsRepository, times(1)).findAllByTeamName("Team A");
        verify(teamPointsRepository, times(0)).save(any(TeamPoints.class));
    }

    @DisplayName("Update Points Team Wins")
    @Test
    public void testUpdatePointsTeamWins() {

        //Given
        String teamName = "Team A";
        TeamPoints team = new TeamPoints();
        team.setTeamName(teamName);
        team.setPoints(10);
        team.setMatchesPlayed(5);
        team.setWins(3);
        team.setDraws(1);
        team.setLosses(1);

        when(teamPointsRepository.findByTeamName(teamName)).thenReturn(team);
        when(teamPointsRepository.save(any(TeamPoints.class))).thenReturn(team);

        //When
        TeamPoints result = teamPointsService.updatePoints(teamName, 3);

        //Then
        assertNotNull(result);
        assertEquals(13, result.getPoints()); //10 + 3 = 13
        assertEquals(6, result.getMatchesPlayed()); //5 + 1 = 6
        assertEquals(4, result.getWins()); //3 + 1 = 4
        assertEquals(1, result.getDraws());
        assertEquals(1, result.getLosses());

        verify(teamPointsRepository, times(1)).findByTeamName(teamName);
        verify(teamPointsRepository, times(1)).save(result);
    }

    @DisplayName("Update Points Match Drawn")
    @Test
    public void tetUpdatePointsMatchDrawn() {

        //Given
        String teamName = "Team A";
        TeamPoints team = new TeamPoints();
        team.setTeamName(teamName);
        team.setPoints(7);
        team.setMatchesPlayed(3);
        team.setWins(2);
        team.setDraws(1);
        team.setLosses(0);

        when(teamPointsRepository.findByTeamName(teamName)).thenReturn(team);
        when(teamPointsRepository.save(any(TeamPoints.class))).thenReturn(team);

        //When
        TeamPoints result = teamPointsService.updatePoints(teamName, 1);

        //Then
        assertNotNull(result);
        assertEquals(8, result.getPoints());
        assertEquals(4, result.getMatchesPlayed()); //3 + 1 = 4
        assertEquals(2, result.getWins());
        assertEquals(2, result.getDraws()); //1 + 1 = 2
        assertEquals(0, result.getLosses());

        verify(teamPointsRepository, times(1)).findByTeamName(teamName);
        verify(teamPointsRepository, times(1)).save(team);
    }

    @DisplayName("Update Points Team Loses")
    @Test
    public void testUpdatePointsTeamLoses() {

        //Given
        String teamName = "Team A";
        TeamPoints team = new TeamPoints();
        team.setTeamName(teamName);
        team.setPoints(8);
        team.setMatchesPlayed(6);
        team.setWins(2);
        team.setDraws(2);
        team.setLosses(2);

        when(teamPointsRepository.findByTeamName(teamName)).thenReturn(team);
        when(teamPointsRepository.save(any(TeamPoints.class))).thenReturn(team);

        //When
        TeamPoints result = teamPointsService.updatePoints(teamName, 0);

        //Then
        assertNotNull(result);
        assertEquals(8, result.getPoints());
        assertEquals(7, result.getMatchesPlayed());
        assertEquals(2, result.getWins());
        assertEquals(2, result.getDraws());
        assertEquals(3, result.getLosses());

        verify(teamPointsRepository, times(1)).findByTeamName(teamName);
        verify(teamPointsRepository, times(1)).save(team);

    }

    @DisplayName("Update Points Invalid Points Value")
    @Test
    public void testUpdatePointsInvalidPointsValue() {

        //Given
        String teamName = "Team A";
        TeamPoints team = new TeamPoints();
        team.setTeamName(teamName);
        team.setPoints(11);
        team.setMatchesPlayed(5);

        when(teamPointsRepository.findByTeamName(teamName)).thenReturn(team);

        //When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                teamPointsService.updatePoints(teamName, 2));

        assertEquals("Points must be 0, 1, or 3.", exception.getMessage());

        verify(teamPointsRepository, times(1)).findByTeamName(teamName);
        verify(teamPointsRepository, never()).save(any(TeamPoints.class));
    }

}