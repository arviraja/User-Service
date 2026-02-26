package com.moviebooking.userservice.service.impl;

import com.moviebooking.userservice.dto.RegisterRequest;
import com.moviebooking.userservice.entity.User;
import com.moviebooking.userservice.repository.UserRepository;
import com.moviebooking.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String registerUser(RegisterRequest request) {
        if(!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("passwords don't match");
        }
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("email already exists");
        }
        if(userRepository.existsByLoginId(request.getLoginId()))
        {
            throw new RuntimeException("loginId already exists");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setLoginId(request.getLoginId());
        user.setPassword(request.getPassword());
        user.setContactNumber(request.getContactNumber());

        String roleRequest = request.getRole();
        if(roleRequest == null || roleRequest.isBlank())
        {
            user.setRole("USER");
        }
        else if(roleRequest != null && roleRequest.equalsIgnoreCase("ADMIN"))
        {
            user.setRole("ADMIN");
        }
        else if(roleRequest !=null && roleRequest.equalsIgnoreCase("USER"))
        {
            user.setRole("USER");
        }
        else{
            throw new RuntimeException("invalid role");
        }
        userRepository.save(user);
        return "User registered successfully";
    }
}
