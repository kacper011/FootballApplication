package com.example.ekstraklasa_api.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.ekstraklasa_api.model.Coach;
import com.example.ekstraklasa_api.repository.CoachRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

class CoachServiceTest {

    @Mock
    private CoachRepository coachRepository;

    @InjectMocks
    private CoachService coachService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Get All Coaches")
    @Test
    public void testGetAllCoaches() {

        //Given
        Coach coach1 = new Coach(1L, "First Coach", "USA", 45);
        Coach coach2 = new Coach(2L, "Second Coach", "Poland", 45);

        List<Coach> mockCoaches = Arrays.asList(coach1, coach2);

        when(coachRepository.findAll()).thenReturn(mockCoaches);

        //When
        List<Coach> coaches = coachService.getAllCoaches();

        //Then
        assertNotNull(coaches);
        assertEquals(2, coaches.size());
        assertEquals("First Coach", coaches.get(0).getName());
        assertEquals("Poland", coaches.get(1).getNationality());

        verify(coachRepository, times(1)).findAll();
    }

}