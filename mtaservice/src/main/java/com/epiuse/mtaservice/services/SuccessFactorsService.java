package com.epiuse.mtaservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import com.epiuse.mtaservice.dto.UserDTO;
import android.net.Uri;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SuccessFactorsService {
    private WebClient webClient;
    private String baseUrl;
    private final String perPersonal = "/PerPersonal";

    // Connect to SuccessFactors api
    public SuccessFactorsService(ObjectMapper objectMapper, WebClient.Builder webClientBuilder,
            @Value("${sf.api.url}") String apiUrl,
            @Value("${sf.api.username}") String username,
            @Value("${sf.api.password}") String password) {

        try {
            this.webClient = webClientBuilder
                    .baseUrl(apiUrl)
                    .defaultHeader("Authorization",
                            "Basic " + java.util.Base64.getEncoder()
                                    .encodeToString((username + ":" + password).getBytes()))
                    .build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.baseUrl = apiUrl;
    }

    // Get user profiles
    public Mono<String> getUserProfiles() {
        String selectFields = "firstName,lastName,personIdExternal";
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + perPersonal)
                .queryParam("$format", "json")
                .queryParam("$top", 10)
                .queryParam("$select", selectFields)
                .build().toUriString();
        System.out.println(url);
        return this.webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> getUserById(String id) {
        String selectFields = "firstName,lastName,personIdExternal";
        String filterFields = "personIdExternal eq " + id;
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + perPersonal)
                .queryParam("$format", "json")
                .queryParam("$top", 10)
                .queryParam("$select", selectFields)
                .queryParam("$filter", filterFields)
                .build().toUriString();

        return this.webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
    }
}