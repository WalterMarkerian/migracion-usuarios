package com.sportclub.migracion_usuarios.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final String secret = "secretoSuperSeguro";
    private final long expiration = 86400000; // 1 día

    public String generateToken(String dni) {
        return Jwts.builder()
                .setSubject(dni)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
