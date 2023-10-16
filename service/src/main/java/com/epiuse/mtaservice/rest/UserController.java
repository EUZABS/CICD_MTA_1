package com.epiuse.mtaservice.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.epiuse.mtaservice.services.UserManager;
import com.epiuse.mtaservice.dto.UserDTO;
import java.util.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserManager userManager;

    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping(value = "/welcome")
    public String handleWelcome(@RequestParam(value = "personIdExternal", defaultValue = "Enthusiast") String personIdExternal) {
        return personIdExternal;
    }

    @GetMapping(value="/users")
    public List<UserDTO> getUsers() {
        return userManager.getUserProfiles();
    }
}