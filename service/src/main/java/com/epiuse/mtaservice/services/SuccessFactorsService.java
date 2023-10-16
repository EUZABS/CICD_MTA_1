package com.epiuse.mtaservice.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import android.net.Uri;
import reactor.core.publisher.Mono;

@Service
public class SuccessFactorsService {
    private WebClient webClient;

    // Connect to SuccessFactors api
    public SuccessFactorsService(WebClient.Builder webClientBuilder, 
                                @Value("${sf.api.url}") String apiUrl,
                                @Value("${sf.api.username}") String username,
                                @Value("${sf.api.password}") String password) {
        try {
        this.webClient = webClientBuilder
                            .baseUrl(apiUrl)
                            .defaultHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((username + ":" + password).getBytes()))
                            .build();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Get user profiles
    public Mono<String> getUserProfiles() {
        try {
        String baseUrl = "https://apisalesdemo8.successfactors.com/odata/v2/PerPersonal";
        String selectFields = "firstName,lastName,personIdExternal"; 
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
        .queryParam("$format", "json")
        .queryParam("$top", 10)
        .queryParam("$select", selectFields)
        .build().toUriString();
        return this.webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
