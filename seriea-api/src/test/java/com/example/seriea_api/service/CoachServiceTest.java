package com.example.seriea_api.service;

import com.example.seriea_api.model.Coach;
import com.example.seriea_api.repository.CoachRepository;
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

    @DisplayName("Get All Coaches Success")
    @Test
    public void testGetAllCoachesSuccess() {

        //Given
        Coach coach1 = new Coach();
        coach1.setId(1L);
        coach1.setName("Coach A");

        Coach coach2 = new Coach();
        coach2.setId(2L);
        coach2.setName("Coach B");

        List<Coach> mockCoaches = Arrays.asList(coach1, coach2);
        when(coachRepository.findAll()).thenReturn(mockCoaches);

        //When
        List<Coach> result = coachService.getAllCoaches();

        //Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Coach A", result.get(0).getName());
        assertEquals("Coach B", result.get(1).getName());

        verify(coachRepository, times(1)).findAll();
    }

    @DisplayName("Get Coach By Id Should Return Coach If Exists")
    @Test
    public void testGetCoachByIdShouldReturnCoachIfExists() {

        //Given
        Long coachId = 1L;
        Coach coach = new Coach();
        coach.setId(coachId);
        coach.setName("Coach A");

        when(coachRepository.findById(coachId)).thenReturn(Optional.of(coach));

        //When
        Coach result = coachService.getCoachById(coachId);

        //Then
        assertNotNull(result);
        assertEquals(coachId, result.getId());
        assertEquals("Coach A", result.getName());

        verify(coachRepository, times(1)).findById(coachId);
    }

    @DisplayName("Get Coach By Id Should Return Null If Not Exists")
    @Test
    public void testGetCoachByIdShouldReturnNullIfNotExists() {

        //Given
        Long coachId = 1L;

        when(coachRepository.findById(coachId)).thenReturn(Optional.empty());

        //When
        Coach result = coachService.getCoachById(coachId);

        //Then
        assertNull(result);

        verify(coachRepository, times(1)).findById(coachId);
    }

    @DisplayName("Save Coach Should Return Saved Coach")
    @Test
    public void testSaveCoachShouldReturnSavedCoach() {

        //Given
        Coach coach = new Coach();
        coach.setId(1L);
        coach.setName("Coach A");

        when(coachRepository.save(coach)).thenReturn(coach);

        //When
        Coach result = coachService.saveCoach(coach);

        //Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Coach A", result.getName());

        verify(coachRepository, times(1)).save(coach);
    }
}