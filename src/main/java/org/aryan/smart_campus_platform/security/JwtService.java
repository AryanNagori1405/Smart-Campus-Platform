package org.aryan.smart_campus_platform.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String email) {

        SecretKey key = Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(
                        System.currentTimeMillis() + 15 * 60 * 1000
                ))
                .signWith(key)
                .compact();
    }
}
