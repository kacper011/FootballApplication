package com.example.bundesliga_api.service;

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

}