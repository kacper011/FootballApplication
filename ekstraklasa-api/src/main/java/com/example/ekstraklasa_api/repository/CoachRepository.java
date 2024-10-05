package com.example.ekstraklasa_api.repository;

import com.example.ekstraklasa_api.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {
}
