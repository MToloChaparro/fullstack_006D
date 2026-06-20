package com.boleta.electronica;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.boleta.electronica.model.Boleta;
import com.boleta.electronica.repository.BoletaRepository;
import com.boleta.electronica.service.impl.BoletaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BoletaServiceImplTest {

    @Mock
    private BoletaRepository boletaRepository;

    @InjectMocks
    private BoletaServiceImpl boletaService;

    @Test
    @DisplayName("GIVEN un archivo Multipart WHEN guardar THEN retorna boleta guardada")
    void testGuardarBoletaExitoso() throws IOException {
        // Arrange
        MultipartFile file = new MockMultipartFile("file", "boleta.pdf", "application/pdf", "contenido_pdf".getBytes());
        Boleta boletaGuardada = new Boleta();
        boletaGuardada.setNombreArchivo("boleta.pdf");

        when(boletaRepository.save(any(Boleta.class))).thenReturn(boletaGuardada);

        // Act
        Boleta resultado = boletaService.guardarBoleta(file);

        // Assert
        assertNotNull(resultado);
        assertEquals("boleta.pdf", resultado.getNombreArchivo());
        verify(boletaRepository, times(1)).save(any(Boleta.class));
    }

    @Test
    @DisplayName("GIVEN ID inexistente WHEN obtener boleta THEN lanza RuntimeException")
    void testObtenerBoletaNoEncontrada() {
        // Arrange
        when(boletaRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> boletaService.obtenerBoletaPorId(99L));
    }
}