package com.example.laliga_api.service;

import com.example.laliga_api.model.Coach;
import com.example.laliga_api.model.Player;
import com.example.laliga_api.model.Team;
import com.example.laliga_api.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @DisplayName("Get All Teams Success")
    @Test
    public void testGetAllTeamsSuceess() {

        //Given
        List<Team> mockTeams = Arrays.asList(
                new Team(1L, "Team A"),
                new Team(2L, "Team B"),
                new Team(3L, "Team C")
        );

        when(teamRepository.findAll()).thenReturn(mockTeams);

        //When
        List<Team> result = teamService.getAllTeams();

        //Then
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("Team A", result.get(0).getName());
        assertEquals("Team B", result.get(1).getName());
        assertEquals("Team C", result.get(2).getName());

        verify(teamRepository, times(1)).findAll();
    }

    @DisplayName("Get Team By Id Success")
    @Test
    public void testGetTeamByIdSuccess() {

        //Given
        Long teamId = 1L;
        Coach mockCoach = new Coach(1L, "Coach A");
        List<Player> mockPlayers = Arrays.asList(
                new Player(1, "Player 1", "Forward", 11, "Poland", 18),
                new Player(2, "Player 2", "Defender", 5, "Germany", 25)
        );

        Team mockTeam = new Team(teamId, "Team A", "Stadium A", mockCoach, mockPlayers);

        mockPlayers.forEach(player ->  player.setTeam(mockTeam));

        when(teamRepository.findById(teamId)).thenReturn(Optional.of(mockTeam));

        //When
        Team result = teamService.getTeamById(teamId);

        //Then
        assertNotNull(result);
        assertEquals(teamId, result.getId());
        assertEquals("Team A", result.getName());
        assertEquals("Stadium A", result.getStadium());
        assertNotNull(result.getCoach());
        assertEquals("Coach A", result.getCoach().getName());
        assertEquals(2, result.getPlayers().size());

        verify(teamRepository, times(1)).findById(teamId);
    }
}