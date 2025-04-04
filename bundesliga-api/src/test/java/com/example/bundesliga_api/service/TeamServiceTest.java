package com.example.bundesliga_api.service;

import com.example.bundesliga_api.model.Coach;
import com.example.bundesliga_api.model.Player;
import com.example.bundesliga_api.model.Team;
import com.example.bundesliga_api.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    @DisplayName("Get All Teams")
    @Test
    public void testGetAllTeams() {

        //Given
        Coach coach1 = new Coach(1L,"Coach A", "USA", 42);
        Coach coach2 = new Coach(2L, "Coach B", "Poland", 50);

        Player player1 = new Player(1, "Neymar", "Forward", 11, "Brasil", 32);
        Player player2 = new Player(2, "Leo Messi", "Forward", 10, "Argentina", 35);

        Team team1 = new Team(1L, "Team A", "Stadium A", coach1, List.of(player1));
        Team team2 = new Team(2L, "Team B", "Stadium B", coach2, List.of(player2));

        List<Team> mockTeams = List.of(team1, team2);
        when(teamRepository.findAll()).thenReturn(mockTeams);

        //When
        List<Team> allTeams = teamService.getAllTeams();

        //Then
        assertNotNull(allTeams);
        assertEquals(2, allTeams.size());

        Team firstTeam = allTeams.get(0);
        Team secondTeam = allTeams.get(1);

        assertEquals("Team A", firstTeam.getName());
        assertEquals("Stadium A", firstTeam.getStadium());
        assertNotNull(firstTeam.getCoach());
        assertEquals("Coach A", firstTeam.getCoach().getName());
        assertEquals(1, firstTeam.getPlayers().size());
        assertEquals("Neymar", firstTeam.getPlayers().get(0).getName());

        assertEquals("Team B", secondTeam.getName());
        assertEquals("Stadium B", secondTeam.getStadium());
        assertNotNull(secondTeam.getCoach());
        assertEquals("Coach B", secondTeam.getCoach().getName());
        assertEquals(1, secondTeam.getPlayers().size());
        assertEquals("Leo Messi", secondTeam.getPlayers().get(0).getName());

        verify(teamRepository, times(1)).findAll();
    }

    @DisplayName("Get Team By Id Team Exists")
    @Test
    public void testGetTeamByIdTeamExists() {

        //Given
        Long teamId = 1L;
        Coach coach = new Coach(1L,"Coach A", "USA", 42);
        Team mockTeam = new Team(teamId, "Team A", "Stadium A", coach, List.of());

        when(teamRepository.findById(teamId)).thenReturn(Optional.of(mockTeam));

        //When
        Team result = teamService.getTeamById(teamId);

        //Then
        assertNotNull(result);
        assertEquals("Team A", result.getName());
        assertEquals("Stadium A", result.getStadium());
        assertNotNull(result.getCoach());
        assertEquals("Coach A", result.getCoach().getName());

        verify(teamRepository, times(1)).findById(teamId);
    }

    @DisplayName("Get Team By Id Team Does Not Exist")
    @Test
    public void testGetTeamByIdTeamDoesNotExist() {

        //Given
        Long teamId = 1L;
        when(teamRepository.findById(teamId)).thenReturn(Optional.empty());

        //When
        Team result = teamService.getTeamById(teamId);

        //Then
        assertNull(result);

        verify(teamRepository, times(1)).findById(teamId);
    }

    @DisplayName("Find By Name Team Exists")
    @Test
    public void testFindByNameTeamExists() {

        //Given
        String teamName = "Team A";
        Coach coach = new Coach(1L,"Coach A", "USA", 42);
        Team mockTeam = new Team(1L, "Team A", "Stadium A", coach, List.of());

        when(teamRepository.findByName(teamName)).thenReturn(mockTeam);

        //When
        Team result = teamService.findByName(teamName);

        //Then
        assertNotNull(result);
        assertEquals(teamName, result.getName());
        assertEquals("Stadium A", result.getStadium());
        assertNotNull(result.getCoach());
        assertEquals("Coach A", result.getCoach().getName());

        verify(teamRepository, times(1)).findByName(teamName);
    }

    @DisplayName("Find By Name Team Does Not Exist")
    @Test
    public void testFindByNameTeamDoesNotExist() {

        //Given
        String teamName = "Nonexistent Team";
        when(teamRepository.findByName(teamName)).thenReturn(null);

        //When
        Team result = teamService.findByName(teamName);

        //Then
        assertNull(result);

        verify(teamRepository, times(1)).findByName(teamName);
    }

    @DisplayName("Save Team")
    @Test
    public void testSaveTeam() {

        //Given
        Coach coach = new Coach(1L,"Coach A", "USA", 42);
        Team teamToSave = new Team(null, "Team A", "Stadium A", coach, List.of());
        Team savedTeam = new Team(1L, "Team A", "Stadium A", coach, List.of());

        when(teamRepository.save(teamToSave)).thenReturn(savedTeam);

        //When
        Team result = teamService.saveTeam(teamToSave);

        //Then
        assertNotNull(result);
        assertEquals("Team A", result.getName());
        assertEquals("Stadium A", result.getStadium());
        assertNotNull(result.getCoach());
        assertEquals("Coach A", result.getCoach().getName());

        verify(teamRepository, times(1)).save(teamToSave);
    }

    @DisplayName("Delete Team")
    @Test
    public void testDeleteTeam() {

        //Given
        Long teamId = 1L;

        //When
        teamService.deleteTeam(teamId);

        //Then
        verify(teamRepository, times(1)).deleteById(teamId);
    }

    @DisplayName("Delete Non Existent Team")
    @Test
    public void testDeleteNonExistentTeam() {

        //Given
        Long teamId = 999L;

        doNothing().when(teamRepository).deleteById(teamId);

        //When
        teamService.deleteTeam(teamId);

        //Then
        verify(teamRepository, times(1)).deleteById(teamId);
    }
}