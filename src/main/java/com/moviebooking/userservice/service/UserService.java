package com.moviebooking.userservice.service;

import com.moviebooking.userservice.dto.LoginRequest;
import com.moviebooking.userservice.dto.LoginResponse;
import com.moviebooking.userservice.dto.RegisterRequest;
import jakarta.servlet.http.HttpServletResponse;


public interface UserService {
    String registerUser(RegisterRequest request);
    LoginResponse login(LoginRequest request, HttpServletResponse response);
}
