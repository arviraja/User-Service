package com.moviebooking.userservice.service;

import com.moviebooking.userservice.dto.LoginRequest;
import com.moviebooking.userservice.dto.LoginResponse;
import com.moviebooking.userservice.dto.RegisterRequest;


public interface UserService {
    String registerUser(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
