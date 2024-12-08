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

}