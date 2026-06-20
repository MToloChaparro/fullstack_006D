package com.autenticador.usuario.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

    
    private final String SECRET_KEY = "4a6f7365406172696153656372657446657932303236426577536563757265546f6be";

    public String createToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        
        return JWT.create()
                .withSubject(username)
                .withIssuer("servicio-auth")
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600000)) // Expira en 1 hora
                .sign(algorithm);
    }
}