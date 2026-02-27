package com.moviebooking.userservice.service;

import com.moviebooking.userservice.dto.LoginRequest;
import com.moviebooking.userservice.dto.RegisterRequest;


public interface UserService {
    String registerUser(RegisterRequest request);
    String login(LoginRequest request);
}
