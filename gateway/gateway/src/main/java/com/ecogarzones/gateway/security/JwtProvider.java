package com.ecogarzones.gateway.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    // Clave de alta seguridad idéntica a la de tu servicio de usuarios
    private final String SECRET_KEY = "4a6f7365406172696153656372657446657932303236426577536563757265546f6be";

    // Valida si el token es estructuralmente correcto y está vigente
    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (Exception e) {
            return false; // Token inválido, expirado o manipulado
        }
    }

    // Extrae el nombre de usuario (subject) contenido dentro del token
    public String extractUsername(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getSubject();
    }
}