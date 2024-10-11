package com.example.seriea_api.repository;

import com.example.seriea_api.model.TeamPoints;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamPointsRepository extends JpaRepository<TeamPoints, Long> {

    TeamPoints findByTeamName(String teamName);

    List<TeamPoints> findAllByTeamName(String teamName);
}
