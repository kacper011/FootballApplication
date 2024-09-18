package com.example.footballservice.football_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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



    @GetMapping("/matches")
    public Mono<String> getAllMatches() {
        return this.webClient.get()
                .uri("/matches")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("/areas")
    public Mono<String> getAllAreas() {

        return this.webClient.get()
                .uri("/areas")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("/competitions")
    public Mono<String> getAllCompetitions() {

        return this.webClient.get()
                .uri("/competitions")
                .retrieve()
                .bodyToMono(String.class);
    }
}



