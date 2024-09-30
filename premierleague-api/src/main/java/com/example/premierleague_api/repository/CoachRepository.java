package com.example.premierleague_api.repository;

import com.example.premierleague_api.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {
}
