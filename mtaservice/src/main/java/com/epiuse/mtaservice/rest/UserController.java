package com.epiuse.mtaservice.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.epiuse.mtaservice.services.UserManager;
import java.util.*;
import reactor.core.publisher.Mono;
import com.epiuse.mtaservice.dto.UserDTO;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserManager userManager;

    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping(value = "/welcome")
    public String handleWelcome(
            @RequestParam(value = "personIdExternal", defaultValue = "Enthusiast") String personIdExternal) {
        return personIdExternal;
    }

    @GetMapping(value = "/allUsers")
    public Mono<String> getUsers(@RequestParam(required = false) String top) {
        return userManager.getUserProfiles(top);
    }

    @GetMapping(value = "/byId/{personIdExternal}")
    public Mono<ResponseEntity<String>> getUserById(@PathVariable String personIdExternal) {
        return userManager.getUserById(personIdExternal)
                .map(response -> ResponseEntity.ok(response))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // @GetMapping(value = "/byName/{name}")
    // public Mono<ResponseEntity<String>> getUserByName(@PathVariable String name)
    // {
    // return userManager.getUserByName(name)
    // .map(response -> ReponseEntity.ok(response))
    // .defaultIfEmpty(ResponseEntity.notFound().build());
    // }
}