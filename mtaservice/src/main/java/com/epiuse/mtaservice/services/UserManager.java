package com.epiuse.mtaservice.services;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;
import com.epiuse.mtaservice.dto.UserDTO;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.epiuse.mtaservice.dto.UserDTOWrapper;

@Component
public class UserManager {

    private final SuccessFactorsService sfService;
    private final ObjectMapper objectMapper;

    public UserManager(ObjectMapper objectMapper, SuccessFactorsService sfService) {
        this.sfService = sfService;
        this.objectMapper = objectMapper;
    }

    public Mono<String> getUserProfiles(String top) {
        return sfService.getUserProfiles(top);
    }

    public Mono<String> getUserById(String id) {
        return sfService.getUserById(id);
    }

    public Mono<String> getUserByName(String name) {
        return sfService.getUserByName(name);
    }

}