package com.example.ekstraklasa_api.service;

import com.example.ekstraklasa_api.model.Team;
import com.example.ekstraklasa_api.repository.TeamRepository;
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
    public void testGetAllTeamSuccess() {

        //Given
        Team team1 = new Team(1L ,"Team A");
        Team team2 = new Team(2L ,"Team B");

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
        Team team = new Team(1L, "Team A");
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        //When
        Team result = teamService.getTeamById(1L);

        //Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Team A", result.getName());

        verify(teamRepository, times(1)).findById(1L);
    }

    @DisplayName("Get Team By Id Not Found")
    @Test
    public void testGetTeamByIdNotFound() {

        //Given
        when(teamRepository.findById(1L)).thenReturn(Optional.empty());

        //When
        Team result = teamService.getTeamById(1L);

        //Then
        assertNull(result);

        verify(teamRepository, times(1)).findById(1L);
    }

    @DisplayName("Find Team By Name Success")
    @Test
    public void testFindTeamByNameSuccess() {

        //Given
        String teamName = "Team A";
        Team team = new Team(1L, teamName);

        when(teamRepository.findByName(teamName)).thenReturn(team);

        //When
        Team result = teamService.findByName(teamName);

        //Then
        assertNotNull(result);
        assertEquals(teamName, result.getName());
        assertEquals(1L, result.getId());;

        verify(teamRepository, times(1)).findByName(teamName);
    }

    @DisplayName("Find Team By Name Not Found")
    @Test
    public void testFindTeamByNameNotFound() {

        //Given
        String teamName = "Team A";

        when(teamRepository.findByName(teamName)).thenReturn(null);

        //When
        Team result = teamService.findByName(teamName);

        //Then
        assertNull(result);

        verify(teamRepository, times(1)).findByName(teamName);
    }

    @DisplayName("Save Team Success")
    @Test
    public void testSaveTeamSuccess() {

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

    @DisplayName("Delete Team Success")
    @Test
    public void testDeleteTeamSuccess() {

        //Given
        Long teamId = 1L;

        //When
        teamService.deleteTeam(teamId);

        //Then
        verify(teamRepository, times(1)).deleteById(teamId);
    }
}