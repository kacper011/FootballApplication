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

}