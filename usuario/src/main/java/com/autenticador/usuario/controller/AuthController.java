package com.autenticador.usuario.controller;

import com.autenticador.usuario.dto.LoginRequest;
import com.autenticador.usuario.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private TokenGenerator tokenGenerator;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        
        String token = tokenGenerator.createToken(request.getUsername());
        
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}