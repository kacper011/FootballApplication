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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

}