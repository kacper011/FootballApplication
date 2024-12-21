package com.example.laliga_api.service;

import com.example.laliga_api.dto.PlayerDTO;
import com.example.laliga_api.mapper.PlayerMapper;
import com.example.laliga_api.model.Player;
import com.example.laliga_api.model.Team;
import com.example.laliga_api.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
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

    @DisplayName("Get All Players Success")
    @Test
    public void testGetAllPlayersSuccess() {

        //Given
        Player player1 = new Player(1, "Player 1", "Forward", 11, "Poland", 18);
        Player player2 = new Player(2, "Player 2", "Forward", 10, "Germany", 24);
        List<Player> players = Arrays.asList(player1, player2);

        PlayerDTO playerDTO1 = new PlayerDTO(1, "Player 1", "Forward", 11, "Poland", 18);
        PlayerDTO playerDTO2 = new PlayerDTO(2, "Player 2", "Forward", 10, "Germany", 24);

        when(playerRepository.findAll()).thenReturn(players);
        try (MockedStatic<PlayerMapper> mockedStatic = mockStatic(PlayerMapper.class)) {
            mockedStatic.when(() -> PlayerMapper.toDTO(player1)).thenReturn(playerDTO1);
            mockedStatic.when(() -> PlayerMapper.toDTO(player2)).thenReturn(playerDTO2);

            //When
            List<PlayerDTO> result = playerService.getAllPlayers();

            //Then
            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(playerDTO1, result.get(0));
            assertEquals(playerDTO2, result.get(1));

            verify(playerRepository, times(1)).findAll();
        }
    }

    @DisplayName("Get Player By Id Success")
    @Test
    public void testGetPlayerByIdSuccess() {

        //Given
        Long playerId = 1L;
        Player player = new Player(1, "Player 1", "Forward", 11, "Poland", 18);

        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));

        //When
        Player result = playerService.getPlayerById(playerId);

        //Then
        assertNotNull(result);
        assertEquals(playerId, result.getId());
        assertEquals("Player 1", result.getName());
        assertEquals("Forward", result.getPosition());
        assertEquals(11, result.getNumber());
        assertEquals("Poland", result.getNationality());
        assertEquals(18, result.getAge());

        verify(playerRepository, times(1)).findById(playerId);
    }

    @DisplayName("Get Player By Id Not Found")
    @Test
    public void testGetPlayerByIdNotFound() {

        //Given
        Long playerId = 1L;

        when(playerRepository.findById(playerId)).thenReturn(Optional.empty());

        //When
        Player result = playerService.getPlayerById(playerId);

        //Then
        assertNull(result);

        verify(playerRepository, times(1)).findById(playerId);
    }

    @DisplayName("Save Player With Team")
    @Test
    public void testSavePlayerWithTeam() {

        //Given
        Long teamId = 1L;
        Team team = new Team(teamId, "Team A");
        Player player = new Player(0, "Player 1", "Forward", 11, "Poland", 18);
        player.setTeam(team);

        Player savedPlayer = new Player(1, "Player 1", "Forward", 11, "Poland", 18);
        savedPlayer.setTeam(team);

        when(teamService.getTeamById(teamId)).thenReturn(team);
        when(playerRepository.save(player)).thenReturn(savedPlayer);

        //When
        Player result = playerService.savePlayer(player);

        //Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(savedPlayer, result);
        assertEquals(team, result.getTeam());

        verify(teamService, times(1)).getTeamById(teamId);
        verify(playerRepository, times(1)).save(player);
    }

    @DisplayName("Save Player Without Team")
    @Test
    public void testSavePlayerWithoutTeam() {

        //Given
        Player player = new Player(0, "Player 1", "Forward", 11, "Poland", 18);
        Player savedPlayer = new Player(1, "Player 1", "Forward", 11, "Poland", 18);

        when(playerRepository.save(player)).thenReturn(savedPlayer);

        //When
        Player result = playerService.savePlayer(player);

        //Then
        assertNotNull(result);
        assertEquals(savedPlayer, result);
        assertNull(result.getTeam());

        verify(playerRepository, times(1)).save(player);
    }
}