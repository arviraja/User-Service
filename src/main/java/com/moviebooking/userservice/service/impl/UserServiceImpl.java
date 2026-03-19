package com.moviebooking.userservice.service.impl;

import com.moviebooking.userservice.config.JwtUtil;
import com.moviebooking.userservice.dto.LoginRequest;
import com.moviebooking.userservice.dto.LoginResponse;
import com.moviebooking.userservice.dto.RegisterRequest;
import com.moviebooking.userservice.entity.User;
import com.moviebooking.userservice.repository.UserRepository;
import com.moviebooking.userservice.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;


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
        user.setPassword(passwordEncoder.encode(request.getPassword()));
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

    @Override
    public LoginResponse login(LoginRequest request, HttpServletResponse response)
    {
        User user;
        if(request.getUsername().contains("@"))
        {
            user = userRepository.findByEmail(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        }
        else {
            user = userRepository.findByLoginId(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        }
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
        {
              throw new RuntimeException("passwords don't match");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String token = jwtUtil.generateToken(user);

//        Cookie cookie = new Cookie("accessToken",token);
//        cookie.setHttpOnly(true);
//        cookie.setSecure(false);
//        cookie.setPath("/");
//        cookie.setMaxAge(60*60);
//        response.addCookie(cookie);

//        return ResponseEntity.ok("Logged in successfully");
        return new LoginResponse(token);
    }
}
