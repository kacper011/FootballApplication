package com.example.bundesliga_api.bundesliga_api.service;

import com.example.bundesliga_api.bundesliga_api.model.Coach;
import com.example.bundesliga_api.bundesliga_api.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService {

    @Autowired
    private CoachRepository coachRepository;

    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    public Coach getCoachById(Long id) {
        return coachRepository.findById(id)
                .orElse(null);
    }

    public Coach saveCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    public void deleteCoach(Long id) {
        coachRepository.deleteById(id);
    }
}
