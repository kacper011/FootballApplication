package com.example.footballservice.football_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/football")
public class FootballController {

    private final WebClient webClient;

    public FootballController(WebClient.Builder webClientBuilder,
                              @Value("${football.api.url}") String baseUrl,
                              @Value("${football.api.key}") String apiKey) {
        this.webClient = webClientBuilder
                .baseUrl(baseUrl)
                .defaultHeader("X-Auth-Token", apiKey)
                .build();
    }

    //MATCHES

    @GetMapping("/matches")
    public Mono<String> getAllMatches() {
        return this.webClient.get()
                .uri("/matches")
                .retrieve()
                .bodyToMono(String.class);
    }

    //AREAS

    @GetMapping("/areas")
    public Mono<String> getAllAreas() {

        return this.webClient.get()
                .uri("/areas")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("/areas/{id}")
    public Mono<String> getAreasById(@PathVariable int id) {

        return this.webClient.get()
                .uri("/areas/" + id)
                .retrieve()
                .bodyToMono(String.class);
    }

    //COMPETITIONS

    @GetMapping("/competitions")
    public Mono<String> getAllCompetitions() {

        return this.webClient.get()
                .uri("/competitions")
                .retrieve()
                .bodyToMono(String.class);
    }

    //TEAMS

    @GetMapping("/teams")
    public Mono<String> getAllTeams() {

        return this.webClient.get()
                .uri("/teams")
                .retrieve()
                .bodyToMono(String.class);
    }

    //PERSON
    // ID = 44 = Cristiano Ronaldo
    @GetMapping("/persons/{id}")
    public Mono<String> getPersonById(@PathVariable int id) {

        return this.webClient.get()
                .uri("/persons/" + id)
                .retrieve()
                .bodyToMono(String.class);
    }


}



