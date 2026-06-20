package com.autenticador.usuario;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

import com.autenticador.usuario.controller.AuthController;
import com.autenticador.usuario.dto.LoginRequest;
import com.autenticador.usuario.security.TokenGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

// 1. Cargamos solo el contexto del Web Layer
@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    // 2. MockMvc es la herramienta estándar para probar controladores sin levantar un servidor real
    @Autowired
    private MockMvc mockMvc;

    // 3. MockBean reemplaza el componente real por uno controlado para el test
    @MockBean
    private TokenGenerator tokenGenerator;

    @Test
    void testLoginRetorna200() throws Exception {
        // Arrange
        LoginRequest request = new LoginRequest("usuario", "password");
        when(tokenGenerator.createToken("usuario")).thenReturn("fake-token");

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk()); // Verificamos que responde 200 OK
    }
}