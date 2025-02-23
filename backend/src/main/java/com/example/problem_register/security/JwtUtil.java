package com.example.problem_register.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "768d7200931c91c2f9d0f651f6502d3a9bdc764a89c812f240400940af5d600bd09a9fd86d1104cda3dee1430bb00ca70b4fd6275ef8dc2c77ddb16783cf36bf764a87aed32c4db4f1463aa1ef69bb3bc06011933101123d885f7edf38e6838c681bde7cff9776dcba4b406796f6f5929bf1d25da5beb2c6272b0c47bedab2f83025e2d992109e1e312f11e6243a4cf77c82b9509431b105a5ea834cab288fef121e6bcec2f3df690e0312d331b46746786d4b77b7ae3938d406c6dfb83fc72866a7b11667b8e364a05bc27af1b8a94e7255ec99d706390982d434525bbad7ba7f16188ea13e2e7074c2accff383b342fabd9b1d1cfe38edc8de094f960fd516"; // Replace with a secure key

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, java.util.function.Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
