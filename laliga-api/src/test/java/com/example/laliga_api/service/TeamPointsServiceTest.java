package com.example.laliga_api.service;

import com.example.laliga_api.model.TeamPoints;
import com.example.laliga_api.repository.TeamPointsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

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
        teamPoints.setTeamName("New Team");
        teamPoints.setPoints(0);
        teamPoints.setMatchesPlayed(0);

        when(teamPointsRepository.findAllByTeamName("New Team")).thenReturn(Collections.emptyList());
        when(teamPointsRepository.save(teamPoints)).thenReturn(teamPoints);

        //When
        TeamPoints result = teamPointsService.addTeam(teamPoints);

        //Then
        assertNotNull(result);
        assertEquals("New Team", result.getTeamName());

        verify(teamPointsRepository, times(1)).findAllByTeamName("New Team");
        verify(teamPointsRepository, times(1)).save(teamPoints);
    }

    @DisplayName("Add Team Already Exists")
    @Test
    public void testAddTeamAlreadyExists() {

        //Given
        TeamPoints existingTeam = new TeamPoints();
        existingTeam.setTeamName("Existing Team");

        when(teamPointsRepository.findAllByTeamName("Existing Team")).thenReturn(Collections.singletonList(existingTeam));

        TeamPoints newTeam = new TeamPoints();
        newTeam.setTeamName("Existing Team");

        //When & Then
        Exception exception = assertThrows(IllegalArgumentException.class, () -> teamPointsService.addTeam(newTeam));

        assertEquals("Team with name Existing Team already exists.", exception.getMessage());

        verify(teamPointsRepository, times(1)).findAllByTeamName("Existing Team");
        verify(teamPointsRepository, never()).save(newTeam);
    }

    @DisplayName("Update Points Success With 3 Points")
    @Test
    public void testUpdatePointsSuccessWith3Points() {

        //Given
        String teamName = "Team A";
        int points = 3;

        TeamPoints team = new TeamPoints();
        team.setTeamName(teamName);
        team.setPoints(0);
        team.setMatchesPlayed(0);
        team.setWins(0);
        team.setDraws(0);
        team.setLosses(0);

        when(teamPointsRepository.findByTeamName(teamName)).thenReturn(team);
        when(teamPointsRepository.save(team)).thenReturn(team);

        //When
        TeamPoints result = teamPointsService.updatePoints(teamName, points);

        //Then
        assertNotNull(result);
        assertEquals(3, result.getPoints());
        assertEquals(1, result.getMatchesPlayed());
        assertEquals(1, result.getWins());
        assertEquals(0, result.getDraws());
        assertEquals(0, result.getLosses());

        verify(teamPointsRepository, times(1)).findByTeamName(teamName);
        verify(teamPointsRepository, times(1)).save(team);
    }

}