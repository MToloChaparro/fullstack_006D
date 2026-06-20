package com.ecogarzones.incidencias;

import com.ecogarzones.incidencias.model.ReporteIncidencia;
import com.ecogarzones.incidencias.model.EstadoIncidencia;
import com.ecogarzones.incidencias.repository.ReporteIncidenciaRepository;
import com.ecogarzones.incidencias.service.ReporteIncidenciaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReporteIncidenciaServiceTest {

    @Mock
    private ReporteIncidenciaRepository repository;

    @InjectMocks
    private ReporteIncidenciaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cuandoCostoEsNegativo_deberiaLanzarExcepcion() {
        // Arrange
        ReporteIncidencia reporteInvalido = new ReporteIncidencia();
        reporteInvalido.setTitulo_incidencia("Test Error");
        reporteInvalido.setCostoEstimado(-100.0);

        // Act & Assert
        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
            service.guardar(reporteInvalido);
        });

        assertEquals("El costo estimado de reparación no puede ser menor a cero.", excepcion.getMessage());
        verify(repository, never()).save(any(ReporteIncidencia.class));
    }

    @Test
    void cuandoGuardarEsExitoso_deberiaRetornarElReporte() {
        // Arrange
        ReporteIncidencia reporteValido = new ReporteIncidencia();
        reporteValido.setTitulo_incidencia("Contenedor Roto");
        reporteValido.setCostoEstimado(5000.0);

        when(repository.save(reporteValido)).thenReturn(reporteValido);

        // Act
        ReporteIncidencia resultado = service.guardar(reporteValido);

        // Assert
        assertNotNull(resultado);
        assertEquals("Contenedor Roto", resultado.getTitulo_incidencia());
        verify(repository, times(1)).save(reporteValido);
    }
}