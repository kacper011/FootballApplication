package com.example.premierleague_api.repository;

import com.example.premierleague_api.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByName(String name);
}
