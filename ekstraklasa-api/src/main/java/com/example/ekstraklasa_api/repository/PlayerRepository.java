package com.example.ekstraklasa_api.repository;

import com.example.ekstraklasa_api.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByTeamId(Long teamId);
}
