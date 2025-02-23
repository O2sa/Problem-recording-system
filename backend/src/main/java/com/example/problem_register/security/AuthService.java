package com.example.problem_register.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.problem_register.user.User;
import com.example.problem_register.user.UserRepository;


@Service
public class AuthService {


   
    private  final  CustomUserDetailsService userDetailsService;

    private final UserRepository userRepository;
   
    private final  AuthenticationManager authenticationManager;

   
    private final  JwtUtil jwtUtil;
   
    private final  PasswordEncoder passwordEncoder;





 

    public AuthService(CustomUserDetailsService userDetailsService, UserRepository userRepository,
            AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String login( LoginOtp authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.username());
        return jwtUtil.generateToken(userDetails.getUsername());
    }

    public String register(RegisterOtp registerRequest) {
        // Check if the username already exists
        if (userRepository.findByUsername(registerRequest.username()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Create a new user and hash the password
        User newUser = new User();
        newUser.setUsername(registerRequest.username());
        newUser.setFullName(registerRequest.fullName());
        newUser.setPassword(passwordEncoder.encode(registerRequest.password()));
        newUser.setStatus(true); // Default status to active

        // Save the user to the database
        userRepository.save(newUser);

        return "User registered successfully";
    }
}



