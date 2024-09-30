package com.example.premierleague_api.repository;

import com.example.premierleague_api.model.TeamPoints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamPointsRepository extends JpaRepository<TeamPoints, Long> {
}
