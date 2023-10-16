package com.epiuse.mtaservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SuccessFactorsService {
    private final WebClient webClient;

    // Connect to SuccessFactors api
    public SuccessFactorsService(WebClient.Builder webClientBuilder, 
                                @Value("${successfactors.api.url}") String apiUrl,
                                @Value("${successfactors.api.username}") String username,
                                @Value("${successfactors.api.password}") String password) {

        this.webClient = webClientBuilder
                            .baseUrl(apiUrl)
                            .defaultHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((username + ":" + password).getBytes()))
                            .build();
    }

    // Get user profiles
    public Mono<String> getUserProfiles() {
        return this.webClient.get()
                .uri("/odata/v2/PerPersonal?$format=json")
                .retrieve()
                .bodyToMono(String.class);
    }
}
