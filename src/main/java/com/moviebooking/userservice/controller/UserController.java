package com.moviebooking.userservice.controller;

import com.moviebooking.userservice.dto.LoginRequest;
import com.moviebooking.userservice.dto.LoginResponse;
import com.moviebooking.userservice.dto.RegisterRequest;
import com.moviebooking.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movieBooking")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody RegisterRequest request)
    {
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request)
    {
        return userService.login(request);
    }
}
