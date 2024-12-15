package com.example.ekstraklasa_api.service;

import com.example.ekstraklasa_api.model.TeamPoints;
import com.example.ekstraklasa_api.repository.TeamPointsRepository;
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
        teamPoints.setTeamName("Team A");
        teamPoints.setPoints(0);

        when(teamPointsRepository.findAllByTeamName("Team A")).thenReturn(Collections.emptyList());
        when(teamPointsRepository.save(teamPoints)).thenReturn(teamPoints);

        //When
        TeamPoints result = teamPointsService.addTeam(teamPoints);

        //Then
        assertNotNull(result);
        assertEquals("Team A", result.getTeamName());
        assertEquals(0, result.getPoints());

        verify(teamPointsRepository, times(1)).findAllByTeamName("Team A");
        verify(teamPointsRepository, times(1)).save(teamPoints);
    }

    @DisplayName("Add Team Team Already Exists")
    @Test
    public void testAddTeamTeamAlreadyExists() {

        //Given
        TeamPoints teamPoints = new TeamPoints();
        teamPoints.setTeamName("Team A");
        teamPoints.setPoints(0);

        when(teamPointsRepository.findAllByTeamName("Team A")).thenReturn(Collections.singletonList(teamPoints));

        //When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> teamPointsService.addTeam(teamPoints));

        assertEquals("Team with name Team A already exists.", exception.getMessage());

        verify(teamPointsRepository, times(1)).findAllByTeamName("Team A");
        verify(teamPointsRepository, never()).save(any(TeamPoints.class));
    }

    @DisplayName("Update Points Success")
    @Test
    public void testUpdatePointsSuccess() {

        //Given
        String teamName = "Team A";
        int points = 3;

        TeamPoints existingTeam = new TeamPoints();
        existingTeam.setTeamName(teamName);
        existingTeam.setPoints(0);
        existingTeam.setMatchesPlayed(0);
        existingTeam.setWins(0);
        existingTeam.setDraws(0);
        existingTeam.setLosses(0);

        when(teamPointsRepository.findByTeamName(teamName)).thenReturn(existingTeam);
        when(teamPointsRepository.save(existingTeam)).thenReturn(existingTeam);

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
        verify(teamPointsRepository, times(1)).save(existingTeam);
    }

    @DisplayName("Update Points Team Not Found")
    @Test
    public void testUpdatePointsTeamNotFound() {

        //Given
        String teamName = "Team A";
        int points = 3;

        when(teamPointsRepository.findByTeamName(teamName)).thenReturn(null);

        //When & Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> teamPointsService.updatePoints(teamName, points));

        assertEquals("Team not found with name: Team A", exception.getMessage());

        verify(teamPointsRepository, times(1)).findByTeamName(teamName);
        verify(teamPointsRepository, never()).save(any(TeamPoints.class));
    }

    @DisplayName("Update Points Invalid Points")
    @Test
    public void testUpdatePointsInvalidPoints() {

        //Given
        String teamName = "Team A";
        int points = 4;

        TeamPoints existingTeam = new TeamPoints();
        existingTeam.setTeamName(teamName);
        existingTeam.setPoints(0);

        when(teamPointsRepository.findByTeamName(teamName)).thenReturn(existingTeam);

        //When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> teamPointsService.updatePoints(teamName, points));

        assertEquals("Points must be 0, 1, or 3.", exception.getMessage());

        verify(teamPointsRepository, times(1)).findByTeamName(teamName);
        verify(teamPointsRepository, never()).save(any(TeamPoints.class));
    }

}