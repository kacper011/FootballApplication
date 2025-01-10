package com.example.premierleague_api.service;

import com.example.premierleague_api.model.Team;
import com.example.premierleague_api.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Get All Teams")
    @Test
    public void testGetAllTeams() {

        //Given
        Team team1 = new Team(1L, "Team A", "Stadium A", null, null);
        Team team2 = new Team(2L, "Team B", "Stadium B", null, null);
        List<Team> teams = Arrays.asList(team1, team2);

        when(teamRepository.findAll()).thenReturn(teams);

        //When
        List<Team> result = teamService.getAllTeams();

        //Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Team A", result.get(0).getName());
        assertEquals("Team B", result.get(1).getName());

        verify(teamRepository, times(1)).findAll();
    }

}