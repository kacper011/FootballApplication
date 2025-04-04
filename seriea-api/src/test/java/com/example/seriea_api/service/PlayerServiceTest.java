package com.example.seriea_api.service;

import com.example.seriea_api.dto.PlayerDTO;
import com.example.seriea_api.mapper.PlayerMapper;
import com.example.seriea_api.model.Player;
import com.example.seriea_api.model.Team;
import com.example.seriea_api.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

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
        Player player1 = new Player(1, "Player 1", "Defender", 6, "Italy", 34);
        Player player2 = new Player(2, "Player 2", "Forward", 15, "Spain", 27);

        List<Player> players = Arrays.asList(player1, player2);

        PlayerDTO playerDTO1 = new PlayerDTO(1, "Player 1", "Defender", 6, "Italy", 34);
        PlayerDTO playerDTO2 = new PlayerDTO(2, "Player 2", "Forward", 15, "Spain", 27);


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
        Player player = new Player(1, "Player 1", "Defender", 6, "Italy", 34);

        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));

        //When
        Player result = playerService.getPlayerById(playerId);

        //Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Player 1", result.getName());
        assertEquals("Defender", result.getPosition());
        assertEquals(6, result.getNumber());
        assertEquals("Italy", result.getNationality());
        assertEquals(34, result.getAge());

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

    @DisplayName("Get Players By Team Id Success")
    @Test
    public void testGetPlayersByTeamIdSuccess() {

        //Given
        Long teamId = 1L;
        List<Player> players = new ArrayList<>();
        players.add(new Player(1, "Player 1", "Defender", 6, "Italy", 34));
        players.add(new Player(2, "Player 2", "Forward", 15, "Spain", 27));

        when(playerRepository.findByTeamId(teamId)).thenReturn(players);

        //When
        List<Player> result = playerService.getPlayersByTeamId(teamId);

        //Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Player 1", result.get(0).getName());
        assertEquals("Player 2", result.get(1).getName());

        verify(playerRepository, times(1)).findByTeamId(teamId);
    }

    @DisplayName("Get Players By Team Id No Players Found")
    @Test
    public void testGetPlayersByTeamIdNoPlayersFound() {

        //Given
        Long teamId = 1L;

        when(playerRepository.findByTeamId(teamId)).thenReturn(new ArrayList<>());

        //When
        List<Player> result = playerService.getPlayersByTeamId(teamId);

        //Then
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(playerRepository, times(1)).findByTeamId(teamId);
    }

    @DisplayName("Save Player With Team Success")
    @Test
    public void testSavePlayerWithTeamSuccess() {

        //Given
        Team team = new Team();
        team.setId(1L);
        team.setName("Team A");

        Player player = new Player(1, "Player 1", "Defender", 6, "Italy", 34);
        player.setTeam(team);

        when(teamService.getTeamById(1L)).thenReturn(team);
        when(playerRepository.save(player)).thenReturn(player);

        //When
        Player result = playerService.savePlayer(player);

        //Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Player 1", result.getName());
        assertEquals(1L, result.getTeam().getId());

        verify(teamService, times(1)).getTeamById(1L);
        verify(playerRepository, times(1)).save(player);
    }

    @DisplayName("Save Player Without Team")
    @Test
    public void testSavePlayerWithoutTeam() {

        //Given
        Long playerId = 1L;
        Player player = new Player(1, "Player 1", "Defender", 6, "Italy", 34);

        when(playerRepository.save(player)).thenReturn(player);

        //When
        Player result = playerService.savePlayer(player);

        //Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Player 1", result.getName());
        assertNull(result.getTeam());

        verify(teamService, times(0)).getTeamById(anyLong());
        verify(playerRepository, times(1)).save(player);

    }

    @DisplayName("Delete Player By ID")
    @Test
    public void testDeletePlayer() {

        //Given
        Long playerId = 1L;

        //When
        playerService.deletePlayer(playerId);

        //Then
        verify(playerRepository, times(1)).deleteById(playerId);
    }

}