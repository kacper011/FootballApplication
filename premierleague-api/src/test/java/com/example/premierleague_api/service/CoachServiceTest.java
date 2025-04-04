package com.example.premierleague_api.service;

import com.example.premierleague_api.model.Coach;
import com.example.premierleague_api.repository.CoachRepository;
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
        List<Coach> mockCoaches = Arrays.asList(
                new Coach(1L, "Coach A", "Poland", 45),
                new Coach(2L, "Coach B", "Germany", 30)
        );

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

    @DisplayName("Get Coach By Id Success")
    @Test
    public void testGetCoachByIdSuccess() {

        //Given
        Long coachId = 1L;
        Coach mockCoach = new Coach(coachId, "Coach A", "Poland", 50);

        when(coachRepository.findById(coachId)).thenReturn(Optional.of(mockCoach));

        //When
        Coach result = coachService.getCoachById(coachId);

        //Then
        assertNotNull(result);
        assertEquals(coachId, result.getId());
        assertEquals("Coach A", result.getName());

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

    @DisplayName("Save Coach Success")
    @Test
    public void testSaveCoachSuccess() {

        //Given
        Coach newCoach = new Coach(null, "New Coach", "Poland", 50);
        Coach savedCoach = new Coach(1L, "New Coach", "Poland", 50);
        when(coachRepository.save(newCoach)).thenReturn(savedCoach);

        //When
        Coach result = coachService.saveCoach(newCoach);

        //Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New Coach", result.getName());
        assertEquals("Poland", result.getNationality());
        assertEquals(50, result.getAge());

        verify(coachRepository, times(1)).save(newCoach);
    }

    @DisplayName("Delete Coach Success")
    @Test
    public void testDeleteCoachSuccess() {

        //Given
        Long coachId = 1L;

        //When
        coachService.deleteCoach(coachId);

        //Then
        verify(coachRepository, times(1)).deleteById(coachId);
    }

}