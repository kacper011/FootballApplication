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

}