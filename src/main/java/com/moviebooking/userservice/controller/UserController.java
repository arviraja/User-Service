package com.moviebooking.userservice.controller;

import com.moviebooking.userservice.dto.RegisterRequest;
import com.moviebooking.userservice.entity.User;
import com.moviebooking.userservice.service.UserService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody RegisterRequest request)
    {
        return userService.registerUser(request);
    }
}
