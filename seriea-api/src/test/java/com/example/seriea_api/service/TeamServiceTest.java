package com.example.seriea_api.service;

import com.example.seriea_api.model.Coach;
import com.example.seriea_api.model.Player;
import com.example.seriea_api.model.Team;
import com.example.seriea_api.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        Coach coach1 = new Coach(1L, "Coach 1", "Germany", 25, null);
        Coach coach2 = new Coach(2L, "Coach 2", "Italy", 40, null);

        Player player1 = new Player(1, "Player 1", "Defender", 6, "Italy", 34);
        Player player2 = new Player(2, "Player 2", "Forward", 11, "Poland", 23);


        Team team1 = new Team(1L, "Team 1", "Stadium 1", coach1, List.of(player1));
        Team team2 = new Team(2L, "Team 2", "Stadium 2", coach2, List.of(player2));

        List<Team> teams = Arrays.asList(team1, team2);

        when(teamRepository.findAll()).thenReturn(teams);

        //When
        List<Team> result = teamService.getAllTeams();

        //Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Team 1", result.get(0).getName());
        assertEquals("Stadium 1", result.get(0).getStadium());
        assertEquals("Coach 1", result.get(0).getCoach().getName());
        assertEquals("Team 2", result.get(1).getName());
        assertEquals("Stadium 2", result.get(1).getStadium());
        assertEquals("Coach 2", result.get(1).getCoach().getName());

        verify(teamRepository, times(1)).findAll();
    }

    @DisplayName("Get Team By Id")
    @Test
    public void testGetTeamById() {

        //Given
        Coach coach = new Coach(1L, "Coach 1", "Germany", 25, null);

        Player player1 = new Player(1, "Player 1", "Defender", 6, "Italy", 34);
        Player player2 = new Player(2, "Player 2", "Forward", 11, "Poland", 23);

        Team team = new Team(1L, "Team 1", "Stadium 1", coach, Arrays.asList(player1, player2));

        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        //When
        Team result = teamService.getTeamById(1L);

        //Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Team 1", result.getName());
        assertEquals("Stadium 1", result.getStadium());
        assertEquals("Coach 1", result.getCoach().getName());
        assertEquals(2, result.getPlayers().size());

        verify(teamRepository, times(1)).findById(1L);
    }

    @DisplayName("Find By Name")
    @Test
    public void testFindByName() {

        //Given
        Coach coach = new Coach(1L, "Coach 1", "Germany", 25, null);

        Player player1 = new Player(1, "Player 1", "Defender", 6, "Italy", 34);
        Player player2 = new Player(2, "Player 2", "Forward", 11, "Poland", 23);

        Team team = new Team(1L, "Team 1", "Stadium 1", coach, Arrays.asList(player1, player2));

        when(teamRepository.findByName("Team 1")).thenReturn(team);

        //When
        Team result = teamService.findByName("Team 1");

        //Then
        assertNotNull(result);
        assertEquals("Team 1", result.getName());
        assertEquals("Stadium 1", result.getStadium());
        assertEquals("Coach 1", result.getCoach().getName());
        assertEquals(2, result.getPlayers().size());

        verify(teamRepository, times(1)).findByName("Team 1");
    }

}