package com.example.bundesliga_api.service;

import com.example.bundesliga_api.dto.PlayerDTO;
import com.example.bundesliga_api.mapper.PlayerMapper;
import com.example.bundesliga_api.model.Player;
import com.example.bundesliga_api.model.Team;
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

    @DisplayName("Get Players By Team Id Team Has Players")
    @Test
    public void testGetPlayersByTeamIdTeamHasPlayers() {

        //Given
        Long teamId = 1L;
        Player player1 = new Player(1, "Neymar", "Forward", 11, "Brasil", 32);
        Player player2 = new Player(2, "Leo Messi", "Forward", 10, "Argentina", 35);
        List<Player> players = Arrays.asList(player1, player2);

        when(playerRepository.findByTeamId(teamId)).thenReturn(players);

        //When
        List<Player> result = playerService.getPlayersByTeamId(teamId);

        //Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(players, result);

        verify(playerRepository, times(1)).findByTeamId(teamId);
    }

    @DisplayName("Get Players By Team Id Team Has No Players")
    @Test
    public void testGetPlayersByTeamIdTeamHasNoPlayers() {

        //Given
        Long teamId = 1L;
        when(playerRepository.findByTeamId(teamId)).thenReturn(Arrays.asList());

        //When
        List<Player> result = playerService.getPlayersByTeamId(teamId);

        //Then
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(playerRepository, times(1)).findByTeamId(teamId);
    }

    @DisplayName("Save Player With Team")
    @Test
    public void testSavePlayerWithTeam() {

        //Given
        Team team = new Team(1L, "Team A", null);
        Player player = new Player(null, "Neymar", 25, "Forward", team);

        Team fetchedTeam = new Team(1L, "Team A", null);
        Player savedPlayer = new Player(1, "Neymar", 25, "Forward", fetchedTeam);

        when(teamService.getTeamById(1L)).thenReturn(fetchedTeam);
        when(playerRepository.save(any(Player.class))).thenReturn(savedPlayer);

        //When
        Player result = playerService.savePlayer(player);

        //Then
        assertNotNull(result);
        assertEquals(savedPlayer, result);
        assertEquals(fetchedTeam, result.getTeam());

        verify(teamService, times(1)).getTeamById(1L);
        verify(playerRepository, times(1)).save(player);
    }

    @DisplayName("Save Player Without Team")
    @Test
    public void testSavePlayerWithoutTeam() {

        //Given
        Player player = new Player(null, "Neymar", 25, "Forward", null);
        Player savedPlayer = new Player(1, "Neymar", 25, "Forward", null);

        when(playerRepository.save(player)).thenReturn(savedPlayer);

        //When
        Player result = playerService.savePlayer(player);

        //Then
        assertNotNull(result);
        assertEquals(savedPlayer, result);
        assertNull(result.getTeam());

        verify(teamService, times(0)).getTeamById(anyLong());
        verify(playerRepository, times(1)).save(player);
    }

    @DisplayName("Delete Player")
    @Test
    public void testDeletePlayer() {

        //Given
        Long playerId = 1L;

        //When
        playerService.deletePlayer(playerId);

        //Then
        verify(playerRepository, times(1)).deleteById(playerId);
    }

    @DisplayName("Update Player When Player Exists")
    @Test
    public void testUpdatePlayerWhenPlayerExists() {

        //Given
        Long playerId = 1L;
        Player existingPlayer = new Player(1, "Neymar", "Forward",  11, "Brasil",  25, null);
        PlayerDTO playerDTO = new PlayerDTO(1, "Updated Name", "Forward", 11, "Brasil",  27, "Team A");
        Team team = new Team(1L, "Team A", null);

        when(playerRepository.findById(playerId)).thenReturn(Optional.of(existingPlayer));
        when(teamService.findByName("Team A")).thenReturn(team);
        when(playerRepository.save(any(Player.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        //When
        Player updatedPlayer = playerService.updatePlayer(playerId, playerDTO);

        //Then
        assertNotNull(updatedPlayer);
        assertEquals("Updated Name", updatedPlayer.getName());
        assertEquals("Forward", updatedPlayer.getPosition());
        assertEquals(11, updatedPlayer.getNumber());
        assertEquals("Brasil", updatedPlayer.getNationality());
        assertEquals(27, updatedPlayer.getAge());
        assertEquals(team, updatedPlayer.getTeam());

        verify(playerRepository, times(1)).findById(playerId);
        verify(playerRepository, times(1)).save(existingPlayer);
        verify(teamService, times(1)).findByName("Team A");
    }

}