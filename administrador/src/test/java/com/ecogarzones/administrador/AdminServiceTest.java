package com.ecogarzones.administrador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecogarzones.administrador.model.Administrador;
import com.ecogarzones.administrador.repository.AdminRepository;
import com.ecogarzones.administrador.service.AdminService;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    @Test
    @DisplayName("GIVEN nuevo admin WHEN crear THEN debe guardar exitosamente")
    void testCrearAdministradorExitoso() {
        // Arrange
        Administrador admin = new Administrador();
        admin.setRutAdministrador(12345678);
        admin.setCorreoAdministrador("test@test.com");
        
        when(adminRepository.existsByRutAdministrador(anyInt())).thenReturn(false);
        when(adminRepository.existsByCorreoAdministrador(anyString())).thenReturn(false);
        when(adminRepository.save(any(Administrador.class))).thenReturn(admin);

        // Act
        Administrador resultado = adminService.crearAdministrador(admin);

        // Assert
        assertNotNull(resultado);
        verify(adminRepository, times(1)).save(admin);
    }

    @Test
    @DisplayName("GIVEN admin existente WHEN crear THEN debe lanzar IllegalArgumentException")
    void testCrearAdministradorDuplicado() {
        // Arrange
        Administrador admin = new Administrador();
        admin.setRutAdministrador(12345678);
        when(adminRepository.existsByRutAdministrador(12345678)).thenReturn(true);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> adminService.crearAdministrador(admin));
    }

    @Test
    @DisplayName("GIVEN ID existente WHEN obtener por ID THEN retorna admin")
    void testObtenerAdministradorPorIdExitoso() {
        // Arrange
        Administrador admin = new Administrador();
        when(adminRepository.findById(1)).thenReturn(Optional.of(admin));

        // Act
        Administrador resultado = adminService.obtenerAdministradorPorId(1);

        // Assert
        assertNotNull(resultado);
        verify(adminRepository, times(1)).findById(1);
    }
}