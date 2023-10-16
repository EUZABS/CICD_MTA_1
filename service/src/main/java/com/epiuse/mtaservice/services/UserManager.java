package com.epiuse.mtaservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.epiuse.mtaservice.dto.UserDTO;

import reactor.core.publisher.Mono;

import java.util.*;

@Component
public class UserManager {

    private final SuccessFactorsService sfService;

    @Autowired
    public UserManager(SuccessFactorsService sfService) {
        this.sfService = sfService;
    }
    
    // Dummy request
    // public List<UserDTO> getUserProfiles() {
    //     List<UserDTO> userProfiles = new ArrayList<>();
    //     userProfiles.add(new UserDTO("10000", "Mr", "Jack", "Black", "M"));
    //     return userProfiles;
    // }

    public Mono<String> getUserProfiles() {
        return sfService.getUserProfiles();
    }

}