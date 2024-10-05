package com.example.ekstraklasa_api.repository;

import com.example.ekstraklasa_api.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByName(String name);
}
