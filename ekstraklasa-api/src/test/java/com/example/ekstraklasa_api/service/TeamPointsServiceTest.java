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

}