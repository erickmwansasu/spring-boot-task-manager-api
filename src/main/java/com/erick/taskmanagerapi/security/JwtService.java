package com.erick.taskmanagerapi.security;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

import com.erick.taskmanagerapi.entity.User;

@Service
public class JwtService {
    @Value("${app.security.jwt.access.token.secret}") String secretKey;
    @Value("${app.security.access.token.expiration:900_000}") long accessTokenExpirationMs;

    public SecretKey getSigningKey() {return Keys.hmacShaKeyFor(secretKey.getBytes());}

    public String generateAccessToken(User user) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
            .subject(user.getUsername())
            .claim("role", user.getRole())
            .claim("userId", user.getId())
            .issuedAt(new Date(now))
            .expiration(new Date(now + accessTokenExpirationMs))
            .signWith(getSigningKey())
            .compact();
    }

    public String extractUsername(String token) {return extractClaim(token, Claims::getSubject);}

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public boolean isTokenValid(UserDetails userDetails, String token) {
        try {
            Claims claims = extractAllClaims(token);
            String username = claims.getSubject();
            Date expiration = claims.getExpiration();

            return username != null
                && username.equals(userDetails.getUsername())
                && expiration.after(new Date());
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
}