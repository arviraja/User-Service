package com.moviebooking.userservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/api/test")
    public String test() {
        return "Authenticated Successfully";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/admin")
    public String admin() {
        return "Admin authenticated successfully";
    }
}
