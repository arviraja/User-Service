package com.moviebooking.userservice.service;

import com.moviebooking.userservice.dto.RegisterRequest;


public interface UserService {
    String registerUser(RegisterRequest request);
}
