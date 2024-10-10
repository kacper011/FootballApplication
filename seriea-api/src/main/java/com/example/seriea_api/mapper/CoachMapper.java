package com.example.seriea_api.mapper;

import com.example.seriea_api.dto.CoachDTO;
import com.example.seriea_api.model.Coach;
import org.springframework.stereotype.Component;

@Component
public class CoachMapper {

    // Konwersja z encji Coach na CoachDTO
    public CoachDTO toDTO(Coach coach) {
        if (coach == null) {
            return null;
        }

        CoachDTO dto = new CoachDTO();
        dto.setId(coach.getId());
        dto.setName(coach.getName());
        dto.setNationality(coach.getNationality());
        dto.setAge(coach.getAge());

        return dto;
    }

    // Konwersja z CoachDTO na encję Coach
    public Coach toEntity(CoachDTO dto) {
        if (dto == null) {
            return null;
        }

        Coach coach = new Coach();
        coach.setId(dto.getId());
        coach.setName(dto.getName());
        coach.setNationality(dto.getNationality());
        coach.setAge(dto.getAge());

        return coach;
    }
}
