package com.example.bundesliga_api.service;

import com.example.bundesliga_api.model.Coach;
import com.example.bundesliga_api.model.Team;
import com.example.bundesliga_api.repository.CoachRepository;
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

class CoachServiceTest {
    @Mock
    private CoachRepository coachRepository;
    @InjectMocks
    private CoachService coachService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Get All Coaches Should Return List Of Coaches")
    @Test
    public void testGetAllCoachesShouldReturnListOfCoaches() {

        //Given
        Coach coach1 = new Coach(1L, "First Coach", "USA", 45);
        Coach coach2 = new Coach(2L, "Second Coach", "Poland", 45);

        Team team1 = new Team(1L, "Team A", coach1);
        Team team2 = new Team(2L, "Team B", coach2);

        coach1.setTeam(team1);
        coach2.setTeam(team2);

        List<Coach> mockCoaches = Arrays.asList(coach1, coach2);

        when(coachRepository.findAll()).thenReturn(mockCoaches);

        //When
        List<Coach> result = coachService.getAllCoaches();

        //Then
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");
        assertEquals("First Coach", result.get(0).getName(), "First coach's name should be First Coach");
        assertEquals("Team B", result.get(1).getTeam().getName(), "Second coach's team should be Team B");

        verify(coachRepository, times(1)).findAll();
    }

    @DisplayName("Get All Coaches Should Return Empty List When No Coaches")
    @Test
    public void getAllCoachesShouldReturnEmptyListWhenNoCoaches() {

        //Given
        when(coachRepository.findAll()).thenReturn(Arrays.asList());

        //When
        List<Coach> result = coachService.getAllCoaches();

        //Then
        assertNotNull(result, "Result should not be null");
        assertTrue(result.isEmpty(), "Result should be an empty list");

        verify(coachRepository, times(1)).findAll();
    }

    @DisplayName("Get Coach By Id Should Return Coach When Exists")
    @Test
    public void getCoachByIdShouldReturnCoachWhenExists() {

        //Given
        Long coachId = 1L;
        Coach coach = new Coach(coachId, "Test Coach", "USA", 40);
        when(coachRepository.findById(coachId)).thenReturn(Optional.of(coach));

        //When
        Coach result = coachService.getCoachById(coachId);

        //Then
        assertNotNull(result, "Result should not be null");
        assertEquals(coachId, result.getId(), "Coach Id should match");
        assertEquals("Test Coach", result.getName(), "Coach name should match");

        verify(coachRepository, times(1)).findById(coachId);
    }

    @DisplayName("Get Coach By Id Should Return Null When Not Exists")
    @Test
    public void getCoachByIdShouldReturnNullWhenNotExists() {

        //Given
        Long coachId = 1L;
        when(coachRepository.findById(coachId)).thenReturn(Optional.empty());

        //When
        Coach result = coachService.getCoachById(coachId);

        //Then
        assertNull(result, "Result should be null when coach does not exist");

        verify(coachRepository, times(1)).findById(coachId);
    }
}