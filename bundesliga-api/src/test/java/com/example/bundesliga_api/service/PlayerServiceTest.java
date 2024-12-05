package com.example.bundesliga_api.service;

import com.example.bundesliga_api.dto.PlayerDTO;
import com.example.bundesliga_api.mapper.PlayerMapper;
import com.example.bundesliga_api.model.Player;
import com.example.bundesliga_api.repository.PlayerRepository;
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

class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private TeamService teamService;
    @InjectMocks
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Get All Players")
    @Test
    public void testGetAllPlayers() {

        //Given
        Player player1 = new Player(1, "Neymar", "Forward", 11, "Brasil", 32);
        Player player2 = new Player(2, "Leo Messi", "Forward", 10, "Argentina", 35);
        List<Player> players = Arrays.asList(player1, player2);

        PlayerDTO playerDTO1 = new PlayerDTO(1, "Neymar", "Forward", 11, "Brasil", 32);
        PlayerDTO playerDTO2 = new PlayerDTO(2, "Leo Messi", "Forward", 10, "Argentina", 35);

        when(playerRepository.findAll()).thenReturn(players);
        mockStatic(PlayerMapper.class);
        when(PlayerMapper.toDTO(player1)).thenReturn(playerDTO1);
        when(PlayerMapper.toDTO(player2)).thenReturn(playerDTO2);

        //When
        List<PlayerDTO> result = playerService.getAllPlayers();

        //Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(playerDTO1, result.get(0));
        assertEquals(playerDTO2, result.get(1));

        verify(playerRepository, times(1)).findAll();
    }

    @DisplayName("Get Player By Id Player Exists")
    @Test
    public void testGetPlayerByIdPlayerExists() {

        //Given
        Long playerId = 1L;
        Player player = new Player(1, "Neymar", "Forward", 11, "Brasil", 32);
        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));

        //When
        Player result = playerService.getPlayerById(playerId);

        //Then
        assertNotNull(result);
        assertEquals(playerId, result.getId());
        assertEquals("Neymar", result.getName());

        verify(playerRepository, times(1)).findById(playerId);
    }

    @DisplayName("Get Player By Id Player Does Not Exist")
    @Test
    public void testGetPlayerByIdPlayerDoesNotExist() {

        //Given
        Long playerId = 1L;
        when(playerRepository.findById(playerId)).thenReturn(Optional.empty());

        //When
        Player result = playerService.getPlayerById(playerId);

        //Then
        assertNull(result);
        verify(playerRepository, times(1)).findById(playerId);
    }

}