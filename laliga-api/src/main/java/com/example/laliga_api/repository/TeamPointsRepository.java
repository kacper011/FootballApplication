package com.example.laliga_api.repository;

import com.example.laliga_api.model.TeamPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamPointsRepository extends JpaRepository<TeamPoints, Long> {

    TeamPoints findByTeamName(String teamName);

    List<TeamPoints> findAllByTeamName(String teamName);
}
