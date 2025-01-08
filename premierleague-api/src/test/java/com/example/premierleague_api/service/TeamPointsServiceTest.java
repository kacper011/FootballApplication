package com.example.premierleague_api.service;

import com.example.premierleague_api.model.TeamPoints;
import com.example.premierleague_api.repository.TeamPointsRepository;
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
        TeamPoints teamPoints = new TeamPoints(null, "New Team", 0, 0, 0, 0, 0);

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

    @DisplayName("Add Team Team Already Exists")
    @Test
    public void testAddTeamTeamAlreadyExists() {

        //Given
        TeamPoints existingTeam = new TeamPoints(1L, "Existing Team", 15, 5, 5, 0, 0);
        TeamPoints newTeam = new TeamPoints(null, "Existing Team", 0, 0, 0, 0, 0);

        when(teamPointsRepository.findAllByTeamName("Existing Team")).thenReturn(List.of(existingTeam));

        //When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> teamPointsService.addTeam(newTeam));

        assertEquals("Team with name Existing Team already exists.", exception.getMessage());

        verify(teamPointsRepository, times(1)).findAllByTeamName("Existing Team");
        verify(teamPointsRepository, never()).save(any(TeamPoints.class));
    }

    @DisplayName("Update Points Success With 3 Points")
    @Test
    public void testUpdatePointsSuccessWith3Points() {

        //Given
        String teamName = "Team A";
        int pointsToAdd = 3;

        TeamPoints existingTeam = new TeamPoints(1L, teamName, 15, 5, 5, 0, 0);

        when(teamPointsRepository.findByTeamName(teamName)).thenReturn(existingTeam);
        when(teamPointsRepository.save(any(TeamPoints.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //When
        TeamPoints result = teamPointsService.updatePoints(teamName, pointsToAdd);

        //Then
        assertNotNull(result);
        assertEquals(18, result.getPoints());
        assertEquals(6, result.getMatchesPlayed());
        assertEquals(6, result.getWins());
        assertEquals(0, result.getDraws());
        assertEquals(0, result.getLosses());

        verify(teamPointsRepository, times(1)).findByTeamName(teamName);
        verify(teamPointsRepository, times(1)).save(existingTeam);
    }

}