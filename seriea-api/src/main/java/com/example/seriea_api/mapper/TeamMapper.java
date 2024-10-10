package com.example.seriea_api.mapper;

import com.example.seriea_api.dto.TeamDTO;
import com.example.seriea_api.model.Player;
import com.example.seriea_api.model.Team;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMapper {

    private final CoachMapper coachMapper;
    private final PlayerMapper playerMapper;

    public TeamMapper(CoachMapper coachMapper, PlayerMapper playerMapper) {
        this.coachMapper = coachMapper;
        this.playerMapper = playerMapper;
    }

    // Konwersja z encji Team na TeamDTO
    public TeamDTO toDTO(Team team) {
        if (team == null) {
            return null;
        }

        TeamDTO dto = new TeamDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setStadium(team.getStadium());
        dto.setCoach(coachMapper.toDTO(team.getCoach()));

        // Konwersja listy graczy na listę PlayerDTO
//        List<PlayerDTO> playerDTOs = team.getPlayers()
//                .stream()
//                .map(playerMapper::toDTO)
//                .collect(Collectors.toList());
//        dto.setPlayers(playerDTOs);

        return dto;
    }

    // Konwersja z TeamDTO na encję Team
    public Team toEntity(TeamDTO dto) {
        if (dto == null) {
            return null;
        }

        Team team = new Team();
        team.setId(dto.getId());
        team.setName(dto.getName());
        team.setStadium(dto.getStadium());
        team.setCoach(coachMapper.toEntity(dto.getCoach()));

        // Konwersja listy PlayerDTO na listę graczy
        List<Player> players = dto.getPlayers()
                .stream()
                .map(playerMapper::toEntity)
                .collect(Collectors.toList());
        team.setPlayers(players);

        // Ustawianie relacji odwrotnych (team) w graczach
        players.forEach(player -> player.setTeam(team));

        return team;
    }
}
