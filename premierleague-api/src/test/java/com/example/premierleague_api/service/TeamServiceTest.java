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

    @DisplayName("Get Team By Id Success")
    @Test
    public void testGetTeamByIdSuccess() {

        //Given
        Team team = new Team(1L, "Team A", "Stadium A", null, null);

        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        //When
        Team result = teamService.getTeamById(1L);

        //Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Team A", result.getName());
        assertEquals("Stadium A", result.getStadium());

        verify(teamRepository, times(1)).findById(1L);
    }

    @DisplayName("Get Team By Id Team Does Not Exist")
    @Test
    public void testGetTeamByIdTeamDoesNotExist() {

        //Given

        when(teamRepository.findById(1L)).thenReturn(Optional.empty());

        //When
        Team result = teamService.getTeamById(1L);

        //Then
        assertNull(result);

        verify(teamRepository, times(1)).findById(1L);
    }

    @DisplayName("Find By Name Team Exists")
    @Test
    public void testFindByNameTeamExists() {

        //Given
        String teamName = "Team A";
        Team team = new Team(1L, teamName, "Stadium A", null, null);

        when(teamRepository.findByName(teamName)).thenReturn(team);

        //When
        Team result = teamService.findByName(teamName);

        //Then
        assertNotNull(result);
        assertEquals(teamName, result.getName());
        assertEquals("Stadium A", result.getStadium());

        verify(teamRepository, times(1)).findByName(teamName);
    }

    @DisplayName("FindByNameTeamDoesNotExist")
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

    @DisplayName("Save Team Should Return Saved Team")
    @Test
    public void testSaveTeamShouldReturnSavedTeam() {

        //Given
        Team team = new Team();
        team.setId(1L);
        team.setName("Team A");

        when(teamRepository.save(team)).thenReturn(team);

        //When
        Team result = teamService.saveTeam(team);

        //Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Team A", result.getName());

        verify(teamRepository, times(1)).save(team);
    }
}