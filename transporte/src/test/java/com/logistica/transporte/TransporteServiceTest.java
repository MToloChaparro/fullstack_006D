package com.logistica.transporte;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.logistica.transporte.model.TipoTransporteEnum;
import com.logistica.transporte.model.Transporte;
import com.logistica.transporte.repository.TransporteRepository;
import com.logistica.transporte.service.TransporteService;

@ExtendWith(MockitoExtension.class)
public class TransporteServiceTest {

    @Mock
    private TransporteRepository transporteRepository;

    @InjectMocks
    private TransporteService transporteService;

    @Test
    @DisplayName("GIVEN tipo de transporte WHEN buscarPorTipo THEN retorna lista filtrada")
    void testBuscarPorTipo() {
        // Arrange
        TipoTransporteEnum tipo = TipoTransporteEnum.STAFF;
        when(transporteRepository.findByTipotransporte(tipo)).thenReturn(Collections.emptyList());

        // Act
        List<Transporte> resultado = transporteService.buscarPorTipo(tipo);

        // Assert
        assertNotNull(resultado);
        verify(transporteRepository, times(1)).findByTipotransporte(tipo);
    }

    @Test
    @DisplayName("GIVEN patente WHEN buscarPorPatente THEN retorna lista")
    void testBuscarPorPatente() {
        // Arrange
        String patente = "ABCD-12";
        when(transporteRepository.findByPatentevehiculo(patente)).thenReturn(Collections.emptyList());

        // Act
        List<Transporte> resultado = transporteService.buscarPorPatente(patente);

        // Assert
        assertTrue(resultado.isEmpty());
        verify(transporteRepository, times(1)).findByPatentevehiculo(patente);
    }
}