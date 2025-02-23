package com.example.problem_register.security;



import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginOtp authRequest) {

        Map<String, Object> response = new HashMap<>();
        response.put("token", authService.login(authRequest));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
     public ResponseEntity hello() {

        Map<String, String> response = new HashMap<>();
        response.put("msg", "hello faf");

        return ResponseEntity.ok(response);
    } 
    
    @GetMapping("/de")
     public ResponseEntity de() {

        Map<String, String> response = new HashMap<>();
        response.put("msg", "hello dd");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterOtp registerRequest) {
        Map<String, Object> response = new HashMap<>();
        response.put("msg", authService.register(registerRequest));
        // Check if the username already exists
        return ResponseEntity.ok(response);
    }
}
