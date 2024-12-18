package com.example.laliga_api.service;

import com.example.laliga_api.model.Coach;
import com.example.laliga_api.repository.CoachRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
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

    @DisplayName("Get All Coaches Success")
    @Test
    public void testGetAllCoachesSuccess() {

        //Given
        Coach coach1 = new Coach(1L, "Coach A", "Poland", 48);
        Coach coach2 = new Coach(2L, "Coach B", "Germany", 40);
        List<Coach> mockCoaches = Arrays.asList(coach1, coach2);

        when(coachRepository.findAll()).thenReturn(mockCoaches);

        //When
        List<Coach> result = coachService.getAllCoaches();

        //Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(coach1, result.get(0));
        assertEquals(coach2, result.get(1));

        verify(coachRepository, times(1)).findAll();
    }

    @DisplayName("Get All Coaches Empty List")
    @Test
    public void testGetAllCoachesEmptyList() {

        //Given
        when(coachRepository.findAll()).thenReturn(Collections.emptyList());

        //When
        List<Coach> result = coachService.getAllCoaches();

        //Then
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(coachRepository, times(1)).findAll();
    }

    @DisplayName("Get Coach By Id Success")
    @Test
    public void testGetCoachByIdSuccess() {

        //Given
        Long coachId = 1L;
        Coach coach = new Coach(coachId, "Coach A", "Poland", 50);

        when(coachRepository.findById(coachId)).thenReturn(Optional.of(coach));

        //When
        Coach result = coachService.getCoachById(coachId);

        //Then
        assertNotNull(result);
        assertEquals(coach, result);
        assertEquals(coachId, result.getId());
        assertEquals("Coach A", result.getName());
        assertEquals("Poland", result.getNationality());
        assertEquals(50, result.getAge());

        verify(coachRepository, times(1)).findById(coachId);
    }

    @DisplayName("Get Coach By Id Not Found")
    @Test
    public void testGetCoachByIdNotFound() {

        //Given
        Long coachId = 1L;

        when(coachRepository.findById(coachId)).thenReturn(Optional.empty());

        //When
        Coach result = coachService.getCoachById(coachId);

        //Then
        assertNull(result);

        verify(coachRepository, times(1)).findById(coachId);
    }

}