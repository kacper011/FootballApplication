package com.example.ekstraklasa_api.controller;

import com.example.ekstraklasa_api.model.Coach;
import com.example.ekstraklasa_api.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ekstraklasa/coaches")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @GetMapping
    public List<Coach> getAllCoaches() {
        return coachService.getAllCoaches();
    }

    @GetMapping("/{id}")
    public Coach getCoachById(@PathVariable Long id) {
        return coachService.getCoachById(id);
    }

    @PostMapping
    public Coach saveCoach(@RequestBody Coach coach) {
        return coachService.saveCoach(coach);
    }

    @DeleteMapping("/{id}")
    public void deleteCoachById(@PathVariable Long id) {
        coachService.deleteCoach(id);
    }
}

