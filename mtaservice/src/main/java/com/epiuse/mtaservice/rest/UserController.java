package com.epiuse.mtaservice.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.attribute.standard.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import com.epiuse.mtaservice.services.UserManager;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import com.epiuse.mtaservice.destination.DestinationConfiguration;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserManager userManager;
    private final DestinationConfiguration destinationConfig;

    @Autowired
    public UserController(UserManager userManager, DestinationConfiguration destinationConfig) {
        this.userManager = userManager;
        this.destinationConfig = destinationConfig;
    }

    @GetMapping(value = "/test")
    public String test() {
        // HttpDestination httpDest = this.destinationConfig.getDestination();
        return "Working";
    }

    @GetMapping(value = "/allUsers")
    public Mono<String> getUsers(@RequestParam(required = false) String top) {
        // HttpDestination httpDest = this.destinationConfig.getDestination();
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