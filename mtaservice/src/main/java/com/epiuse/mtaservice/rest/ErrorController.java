package com.epiuse.mtaservice.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
    @GetMapping(value = "/error")
    public String errorOutput() {
        return "Could not find that URL; please try again";
    }
}
