package com.epiuse.mtaservice.services;

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

    // Dummy request
    // public List<UserDTO> getUserProfiles() {
    // List<UserDTO> userProfiles = new ArrayList<>();
    // userProfiles.add(new UserDTO("10000", "Mr", "Jack", "Black", "M"));
    // return userProfiles;
    // }

    public Mono<String> getUserProfiles() {
        return sfService.getUserProfiles();
        // .map(returnArray -> {
        // System.out.println(returnArray);
        // try {
        // UserDTOWrapper wrapper = objectMapper.readValue(returnArray,
        // UserDTOWrapper.class);
        // List<UserDTO> userDTOList = wrapper.getResults();
        // return userDTOList;
        // } catch (Exception e) {
        // // Handle the exception (e.g., log it) and return an empty array or throw an
        // // error
        // System.out.println(e.getMessage());
        // return null;
        // }
        // })
        // .block();

    }

}